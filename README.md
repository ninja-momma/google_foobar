# google_foobar

Minglish Challenge
===================
Given a sorted list of dictionary words (you know they are sorted because you can read the page numbers), can you find the alphabetical order of the Minglish alphabet?  For example, if the words were ["z", "yx", "yz"] the alphabetical order would be "xzy", which means x < z < y.  The first two words tell you that z < y, and the last two words tell you that x < z.

Write a function which, given a list of words sorted alphabetically in the Minglish alphabet, outputs a string that contains each letter present in the list of words exactly once; the order of the letters in the output must follow the order of letters in the Minglish alphabet.

The list will contain at least 2 and no more than 50 words, and each word will consist of at least 1 and no more than 50 lowercase letters [a-z].  It is guaranteed that a total ordering can be developed from the input provided (i.e. given any two distinct letters, you can tell which is greater), and so the answer will exist and be unique.

Test cases
=====================
Inputs: (string list) words = ["y", "z", "xy"]

Output: (string) "yzx"



Inputs: (string list) word = ["ba", "ab", "cb"]

Output: (string) "bac"
