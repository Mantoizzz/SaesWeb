# S-AES

## 项目截图

### 基础加密解密

![image-20231031212944792](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20231031212944792.png)

![image-20231031212956590](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20231031212956590.png)

### ASCII

![image-20231031213023644](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20231031213023644.png)

![image-20231031213034067](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20231031213034067.png)

### 二重加密

![image-20231031213159223](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20231031213159223.png)

![image-20231031213209795](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20231031213209795.png)

### 三重加密

![image-20231031213255972](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20231031213255972.png)

![image-20231031213309703](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20231031213309703.png)

### 中间相遇攻击

```java
@Test
    void testForMeetInMiddleAttack() throws Exception {
        int[][] arrPlain = {
                {0x5, 0x8},
                {0xA, 0x2}
        };

        int[][] encryptText = {
                {0x1, 0x7},
                {0x9, 0x6}
        };
        List<int[][]> result = MeetInMiddle.meetInMiddleAttack(arrPlain, encryptText);
        /*
        由于result集合中含有大量密钥，这里不作输出
         */
    }
```

### CBC

```java
@Test
    void testForCBC() throws Exception {
        int[][] arrSecret = {
                {0xA, 0x3},
                {0x7, 0xB}
        };
        int[][] arrPlainA = {
                {0x5, 0x8},
                {0xA, 0x2}
        };
        int[][] arrPlainB = {
                {0x1, 0x7},
                {0x9, 0x6}
        };
        int[][] arrPlainC = {
                {0x8, 0x2},
                {0xB, 0x1}
        };
        List<int[][]> list = new ArrayList<>();
        list.add(arrPlainA);
        list.add(arrPlainB);
        list.add(arrPlainC);
        System.out.println("CBC.encrypt(list, arrSecret) = " + CBC.encrypt(list, arrSecret));
    }
```

![image-20231031212854717](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20231031212854717.png)

## 用户指南

用IntelliJ IDEA打开项目后，等待Maven下载相关的所需的库(Thymeleaf,Springboot相关依赖)

等待所有工作准备就绪后，启动SpringbootApplication

打开你的浏览器，输入`localhost:8080/index`

页面中出现4个导航，选择一个就进入了对应的加密标准



## 注意事项

由于CBC链和中间相遇攻击这两个关卡写在web应用上比较麻烦（主要是我很懒），可以直接在SpringBoot测试类中进行测试



要进入SpringBoot测试类，在src/test/java/com/aes/saesweb/SaesWebApplicationTests.java中，找到有@Test注解的函数，右键运行函数，查看控制台输出就OK



除此之外，其他关卡也有对应的测试函数，编写测试函数也很简单,在你的函数上使用@Test注解就写好了一个测试函数

## 项目结构

### Core

存储S-AES基础的加密算法和解密算法的函数，其中Process类包含了大多需要的函数

### expand

存储ASCII加密，CBC工作链模式的函数和类入口

### ui

存储SpringWeb的相关控制器，负载类，REST控制器的相关

### Constans

存储S-AES算法中的所有常量

### SaesWebApplication

SpringBoot应用的入口

