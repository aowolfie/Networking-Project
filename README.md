# Networking-Project
Due on 12/07/16

#1. Hamming Method for Error Correction
##1.1 Hamming code generation
Write a program that can convert a bit stream of a packet into a frame by using Hamming coding. The bit stream is the input parameter of the program. For example, given an input parameter as 1001000, the correct output of the frame is 00110010000. Input bit stream will have 1 to 64 bits.
Some self-testing examples are: 

       Input      | Output
------------------| -------------
1001000           | 00110010000 
1101001100110101  | 011110110011001110101
1010101           | 11110100101
11111111          | 111011101111

##1.2 Hamming code checking
Write a program in that can correct a single-bit error of a frame by using Hamming coding and output the packet (raw data). The incoming frame is the input parameter of the program. The output is the bit stream of a packet. For example, given an input parameter as 00110010001 (the correct frame should actually be 00110010000), the output bit stream is 1001000. Input frame will have 3 to 71 bits.
Some self-testing examples are: 

       Input          | Output
----------------------| -------------
00110010000           | 1001000 
00110010001           | 1001000
011110110011001110101 | 1101001100110101
11110100101           | 1010101
111011101111          | 11111111
11110000101           | 1010101

#2. CRC for Error Detection
Write a program in that can convert a bit stream of a packet into a frame by using CRC. The bit stream is the first input parameter of the program, which will have 8 to 128 bits. The generator function is the second input parameter of the program, which will have 4 to 16 bits. For example, if the generator function is x^4 + x + 1, the second input will be 10011. The output should be the transmitted bit stream with its CRC checksum bits; see example in Figure 3-9 in the textbook.
Some self-testing examples are: 


       Input     | Generator        |Output
-----------------| -----------------|---------------
10011101         | 1001             |10011101100
1101011011       | 10011            |11010110111110
10011101         | 1001             |10011101100
1101011011011100 | 1101110111011101 |1101011011011100110101101101110
