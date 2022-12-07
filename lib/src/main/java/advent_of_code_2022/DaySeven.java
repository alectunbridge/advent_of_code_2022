package advent_of_code_2022;

import java.util.*;

class Directory {

    private String name;
    private int size;
    private Set<String> subdirectories;
    private Directory parent;

    public Directory(String name, Directory parent) {
        this.subdirectories = new HashSet<>();
        this.name = name;
        this.parent = parent;
    }

    public void addToSize(int size) {
        this.size += size;
    }

    public int getSize() {
        int subDirectorySizes = subdirectories.stream().mapToInt(sub -> DaySeven.directories.get(name+sub).getSize()).sum();
        return size + subDirectorySizes;
    }

    public String getName() {
        return name;
    }

    public void addSubDirectory(String subdirectory) {
        subdirectories.add(subdirectory);
    }

    public Directory getParent() {
        return parent;
    }
}

public class DaySeven {
    private List<String> input;
    static Map<String, Directory> directories;


    public DaySeven(List<String> input) {
        directories = new HashMap<>();
        this.input = input;
    }

    public int solvePart1() {
        Directory currentDirectory = new Directory("",null);
        for (int i = 0; i < input.size(); ) {
            if (input.get(i).matches("\\$ cd ..")){
                currentDirectory = currentDirectory.getParent();
            }
            if (input.get(i).matches("\\$ cd [^.]+")) {
                currentDirectory = new Directory(currentDirectory.getName()+input.get(i).split(" ")[2],currentDirectory);
                i++;
                while (i<input.size()) {
                    if (input.get(i).matches("\\$ ls")){
                        //skip
                    } else if (input.get(i).matches("^\\d.*")) {
                        currentDirectory.addToSize(Integer.parseInt(input.get(i).split(" ")[0]));
                    } else if (input.get(i).matches("dir \\w+")){
                        currentDirectory.addSubDirectory(input.get(i).split(" ")[1]);
                    } else if (input.get(i).startsWith("$")){
                        directories.put(currentDirectory.getName(), currentDirectory);
                        i--;
                        break;
                    }
                    i++;
                }
            }
            i++;
        }
        directories.put(currentDirectory.getName(), currentDirectory);
        return directories.values().stream().mapToInt(Directory::getSize).filter(i->i<=100000).sum();
    }
}
