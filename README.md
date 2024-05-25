# Втора лабораториска вежба по Софтверско инженерство
## Марио Митевски, бр. на индекс 213222
### Control Flow Graph
![flowgraph](https://github.com/mmitevski0/SI_2024_lab2_213222/assets/162879745/dc766854-19b4-4688-8885-d21ee754645a)
### Цикломатска комплексност
Цикломатската комплексност на овој код е 10, истата ја добив преку формулата P+1, каде што P е бројот на предикатни јазли. Во случајoв P=9, па цикломатската комплексност изнесува 10.
### Тест случаи според критериумот Every Branch
<font size=3>**testCase1:**</font> allItems = null, payment = any
<br> **Passed edges:** 1-2, 2-19
<br>
<br>
<font size=3>**testCase2:**</font> allItems = [(name = null, barcode = "123g45", price = any, discount = any)], payment = any
<br> **Passed edges:** 1-3.1, 3.1-3.2, 3.2-4, 4-5, 5-6, 6-7.1, 7.1-7.2, 7.2-8, 8-7.3, 7.3-7.2, 8-9, 9-19
<br>
<br>
<font size=3>**testCase3:**</font> allItems = [(name = any, barcode = "0123", price = 350, discount = 0.5)], payment = 200
<br> **Passed edges:** 1-3.1, 3.1-3.2, 3.2-4, 3.2-16, 4-6, 6-7.1, 7.1-7.2, 7.2-8, 7.2-10, 8-7.3, 7.3-7.2, 10-11, 11-14, 14-15, 15-3.3, 3.3-3.2, 16-17, 17-19
<br>
<br>
<font size=3>**testCase4:**</font> allItems = [(name = any, barcode = null, price = any, discount = any)], payment = any
<br> **Passed edges:** 1-3.1, 3.1-3.2, 3.2-4, 4-6, 6-13, 13-19
<br>
<br>
<font size=3>**testCase5:**</font> allItems = [(name = any, barcode = any, price = 150, discount = 0)], payment = 100
<br> **Passed edges:** 1-3.1, 3.1-3.2, 3.2-4, 3.2-16, 4-6, 6-7.1, 7.1-7.2, 7.2-8, 7.2-10, 8-7.3, 7.3-7.2, 10-12, 12-14, 14-3.3, 3.3-3.2, 16-18, 18-19
<br>
<br>
***Со овие 5 теста сме сигурни дека секоја гранка на кодот се извршува барем еднаш односно е постигната целосна покриеност.***
### Тест случаи според критериумот Multiple Condition
Услов: *if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')*

Решение: Потребно е сите три услови да бидат точни (T) бидејќи тие меѓусебно се поврзани со И (&&). Во секој друг случај се добива неточен израз.

1. **T && T && T** <br><br>
price = 350, discount = 0.1, barcode = "0123456789" <br>
Трите услови се исполнети и се очекува целиот израз да е точен (T).<br><br>
2. **F && X && X** <br><br>
   price = 200, discount = any, barcode = any<br>
Првиот услов е неточен и се очекува целиот израз да е неточен (F). <br><br>
3. **X && F && X** <br><br>
price = any, discount = 0, barcode = any <br>
Вториот услов е неточен и се очекува целиот израз да е неточен (F). <br><br>

4. **X && X && F** <br><br>
price = any, discount = any, barcode = "123456789" <br>
Третиот услов е неточен и се очекува целиот израз да е неточен (F).
### Објаснување на напишаните unit tests
Unit тестовите ги напишав и тестират користејќи го Gradle во InteliJ. Во SILab2Test.java дефинирав две методи, testEveryBranch() и testMultipleCondition().
<br><br>
Во testEveryBranch() методот се напишани минималниот број на тестови со кој се одредува однесувањето на програмата според Every Branch критериумот.
<br><br>
Во testMultipleCondition() се напишани минималниот број на комбинации со кој се одредува однесувањето на условот: *if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')* според Multiple Condition критериумот.
