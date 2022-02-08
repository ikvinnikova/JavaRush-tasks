package com.javarush.task.task26.task2608;

/* 
Мудрый человек думает раз, прежде чем два раза сказать
*/

public class Solution {
    int var1;
    int var2;
    int var3;
    int var4;

    public Solution(int var1, int var2, int var3, int var4) {
        synchronized ((Object) this.var1) {
            this.var1 = var1;
        }
        synchronized ((Object) this.var2) {
            this.var2 = var2;
        }
        synchronized ((Object) this.var3) {
            this.var3 = var3;
        }
        synchronized ((Object) this.var4) {
            this.var4 = var4;
        }
    }

    public int getSumOfVar1AndVar2() {
        int result = 0;
        synchronized ((Object)var1) {
            result = var1 + var2;
        }
        return result;
    }

    public int getSumOfVar3AndVar4() {
        int result = 0;
        synchronized ((Object)var3) {
            result = var3 + var4;
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
