package advent_of_code_2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaySeven {
    List<String> input;

    public DaySeven(List<String> input) {
        this.input = input;
    }

    public int solvePart1() {
        FileSystem fileSystem = new FileSystem();

        fileSystem.readInput(input);

        return fileSystem.findSumOfValidDirectories();
    }

    public int solvePart2() {
        FileSystem fileSystem = new FileSystem();

        fileSystem.readInput(input);

        final List<Integer> sortedDirectories = fileSystem.fetchDirectoriesInAscendingOrder();

        final Integer totalSizeOfDir = sortedDirectories.get(sortedDirectories.size() - 1);
        final Integer totalSizeOfDisk = 70000000;
        final Integer currentUnusedSpace = totalSizeOfDisk - totalSizeOfDir;
        final Integer spaceNeededToFreeUp = 30000000 - currentUnusedSpace;
        return sortedDirectories.stream().filter(directory -> directory > spaceNeededToFreeUp).findFirst().get();
    }

    public class FileSystem {

        private final Directory rootDirectory;
        private Directory currentDirectory;

        public FileSystem() {
            final Directory rootDir = new Directory("/");
            rootDirectory = rootDir;
            currentDirectory = rootDir;
        }

        public void readInput(List<String> input) {
            for (String line : input) {
                final String[] splitLine = line.split(" ");
                if (line.contains("$ cd")) {
                    cd(splitLine[2]);
                } else {
                    if (!line.equals("$ ls")) {
                        if (line.contains("dir")) {
                            addDirectoryToCurrentDirectory(splitLine[1]);
                        } else {
                            addFileToCurrentDirectory(splitLine[1], Integer.parseInt(splitLine[0]));
                        }
                    }
                }
            }
        }

        public void cd(String directoryName) {
            if (directoryName.equals("/")) {
                currentDirectory = rootDirectory;
            } else if (directoryName.equals("..")) {
                currentDirectory = currentDirectory.moveUpOneLevel();
            } else {
                currentDirectory = currentDirectory.moveToDirectory(directoryName);
            }
        }

        public void addDirectoryToCurrentDirectory(String name) {
            currentDirectory.addDirectory(name);
        }

        public void addFileToCurrentDirectory(String name, int size) {
            currentDirectory.addFile(name, size);
        }

        public int findSumOfValidDirectories() {
            Map<Directory, Integer> directoryToSize = new HashMap<>();

            final int total = findTotalSizeAtDirectoryLevel(rootDirectory, directoryToSize);
            directoryToSize.put(rootDirectory, total);

            return directoryToSize.values().stream().filter(size -> size < 100000).mapToInt(i -> i).sum();
        }

        public List<Integer> fetchDirectoriesInAscendingOrder() {
            Map<Directory, Integer> directoryToSize = new HashMap<>();

            final int total = findTotalSizeAtDirectoryLevel(rootDirectory, directoryToSize);
            directoryToSize.put(rootDirectory, total);

            return directoryToSize.values().stream().sorted().toList();
        }

        public int findTotalSizeAtDirectoryLevel(Directory directory, Map<Directory, Integer> result) {
            if (directory.hasSubdirectories()) {
                final List<Directory> subdirectories = directory.fetchSubdirectories();
                for (Directory subdirectory : subdirectories) {
                    result.put(subdirectory, findTotalSizeAtDirectoryLevel(subdirectory, result));
                }
            }
            return directory.calculateSize();
        }
    }

    public class Directory {

        private final String name;
        private final Directory parentDirectory;
        private final Map<String, Directory> subdirectories = new HashMap<>();
        private final List<File> files = new ArrayList<>();

        public Directory(String name) {
            this.name = name;
            this.parentDirectory = this;
        }

        public Directory(String name, Directory parentDirectory) {
            this.name = name;
            this.parentDirectory = parentDirectory;
        }

        public void addDirectory(String name) {
            subdirectories.put(name, new Directory(name, this));
        }

        public void addFile(String name, int size) {
            files.add(new File(name, size));
        }

        public Directory moveUpOneLevel() {
            return parentDirectory;
        }

        public Directory moveToDirectory(String name) {
            return subdirectories.get(name);
        }

        public boolean hasSubdirectories() {
            return this.subdirectories.size() > 0;
        }

        public List<Directory> fetchSubdirectories() {
            return this.subdirectories.values().stream().toList();
        }

        private int calculateSize() {
            int totalSizeOfFilesInSubdirectories = 0;

            for (Directory dir : subdirectories.values()) {
                totalSizeOfFilesInSubdirectories += dir.calculateSize();
            }

            int sizeOfFilesInCurrentDirectory = 0;

            for (File file : files) {
                sizeOfFilesInCurrentDirectory += file.getSize();
            }
            return totalSizeOfFilesInSubdirectories + sizeOfFilesInCurrentDirectory;
        }
    }

    public class File {

        private String name;
        private int size;

        public File(String name, int size) {
            this.name = name;
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }
}
