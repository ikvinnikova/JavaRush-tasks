package com.javarush.task.task36.task3611;

import java.util.HashSet;
import java.util.Set;

/* 
Сколько у человека потенциальных друзей?
*/

public class Solution {
    private boolean[][] humanRelationships;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.humanRelationships = generateRelationships();

        Set<Integer> allFriendsAndPotentialFriends = solution.getAllFriendsAndPotentialFriends(7, 2);
        System.out.println(allFriendsAndPotentialFriends);                              // Expected: [0, 1, 2, 3, 5, 7]
        Set<Integer> potentialFriends = solution.removeFriendsFromSet(allFriendsAndPotentialFriends, 7);
        System.out.println(potentialFriends);                                           // Expected: [2, 5, 7]
    }

    public Set<Integer> getAllFriendsAndPotentialFriends(int index, int deep) {
        //напишите тут ваш код
        Set<Integer> result = new HashSet<>();
        if (deep < 1 || deep > humanRelationships.length) {
            return result;
        }

        recursiveFriends(result, index, deep);
        result.remove(index);
        return result;

    }

    public void recursiveFriends(Set<Integer> result, int index, int deep) {
        if (deep > 0) {
            for (int j = 0; j < humanRelationships[index].length; j++) {
                if (j != index) {
                    if (humanRelationships[index][j]) {
                        if (!result.contains(j)) {
                            result.add(j);
                        }
                        recursiveFriends(result, j, deep - 1);
                    }
                }
            }
            for (int i = 0; i < humanRelationships.length; i++) {
                if (index < humanRelationships[i].length) {
                    if (i != index) {
                        if (humanRelationships[i][index]) {
                            if (!result.contains(i)) {
                                result.add(i);
                            }
                            recursiveFriends(result, i, deep - 1);
                        }
                    }
                }
            }
        }
    }

    // Remove from the set the people with whom you already have a relationship
    public Set<Integer> removeFriendsFromSet(Set<Integer> set, int index) {
        for (int i = 0; i < humanRelationships.length; i++) {
            if ((i < index) && (index < humanRelationships.length) && humanRelationships[index][i]) {
                set.remove(i);
            } else if ((i > index) && humanRelationships[i][index]) {
                set.remove(i);
            }
        }
        return set;
    }

    // Return test data
    private static boolean[][] generateRelationships() {
        return new boolean[][]{
                {true},                                                                 //0
                {true, true},                                                           //1
                {false, true, true},                                                    //2
                {false, false, false, true},                                            //3
                {true, true, false, true, true},                                        //4
                {true, false, true, false, false, true},                                //5
                {false, false, false, false, false, true, true},                        //6
                {false, false, false, true, false, false, false, true}                  //7
        };
    }
}