package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DaySixTest {
    
    
    @Test
    void examplesPart1(){
        DaySix daySix = new DaySix(List.of(
                "bvwbjplbgvbhsrlpgdmjqwftvncz"
        ));
        assertThat(daySix.solvePart1()).isEqualTo(5);
    }
    
    @Test
    void examplesPart2(){
        DaySix daySix = new DaySix(List.of(
                "nppdvjthqldpwncqszvftbrmjlhg"
        ));
        assertThat(daySix.solvePart1()).isEqualTo(6);
    }
    
    @Test
    void examplesPart3(){
        DaySix daySix = new DaySix(List.of(
                "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
        ));
        assertThat(daySix.solvePart1()).isEqualTo(10);
    }
    
    @Test
    void examplesPart4(){
        DaySix daySix = new DaySix(List.of(
                "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
        ));
        assertThat(daySix.solvePart1()).isEqualTo(11);
    }
    
    @Test
    void solvePart1(){
        DaySix daySix = new DaySix(Utils.readInputLinesFromFile("day_six.txt"));
        assertThat(daySix.solvePart1()).isEqualTo(1343);
    }

}