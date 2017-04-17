In readme.txt file, you should briefly explain how to compile and execute the source
code you submit. You should use JAVA language, but C language using pthread library is also
allowed.


problem1
    import PrimeDynamicThread
    import PrimeStaticThread
    run Main.class [Thread num] [Option]

    [Thread num] - 실행 시킬 스레드 갯수
    [Option] - 0 or 1이며, 0은 static, 1은 dynamic으로 동작한다.

    ex - Main.class 4 0

problem2
    import MatrixMulThread
    run Main.class [Flie] [Thread Num]

    [File] - Main.class와 동일한 위치에 있는 파일 명을 넣으면 된다.
    [Thread Num] - 실행 시킬 스레드 갯수

    ex - Main.class mat5.txt 4