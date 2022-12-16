# bitSaver

Khi làm việc với các server Java sử dụng log4j cũng như làm việc trong môi trường fintech, mình nhận ra rằng đặc thù của log cty mình phải được lưu trữ trong thời gian có thể lên tới 5 năm tuỳ theo chính sách của các tổ chức tài chính cty mình hợp tác.

Từ đó cần có một phương pháp tốt hơn để lưu trữ nhưng file log này.

**Project này chỉ là một ý tưởng thoáng qua, và nó không được áp dụng trong thực tế.**

## Giới thiệu

khi nhìn vào các file log, đó là những file text thuần có encoding là UTF-8, với việc sử dụng 8 bit bộ nhớ đại diện cho mỗi ký tự.

Nhìn sâu hơn nữa vào file mình nhận thấy là chúng ta chỉ cần những chữ cái alphabet, số ả rập, dấu chấm câu và các ký tự tiện ích. Những ký tự đó chúng nằm trong nửa của bảng mã.

![https://www.jmix.io/uploads/fa96286506be47f58c4761ef92268d28.PNG](https://www.jmix.io/uploads/fa96286506be47f58c4761ef92268d28.PNG)

Điều này có nghĩa là một ký tự chỉ cần đến 7 bit để đại diện cho nó. Nhưng đơn vị thấp nhất mà máy tính quản lý lưu trữ là 1 byte tương đương với 8 bit.

## Ý tưởng

Ý tưởng nảy ra là sử dụng bit cao nhất trong 8 bit để lưu trữ byte thứ 8 trong file

![https://raw.githubusercontent.com/anhdungxd21/bitSaver/main/img/bit_order.png](https://raw.githubusercontent.com/anhdungxd21/bitSaver/main/img/bit_order.png)

Từ 8 byte ban đầu ta có thể mã hoá nó trở thành 7 byte và làm ngược lại cho việc giải mã.

Theo lý thuyết, chúng ta có thể rút ngắn được 1/8 độ dài của file tức là vào khoảng 12.5%.

Vì sao lại “khoảng” vì đôi khi số lượng byte cuối cùng trong file k đủ chia hết cho 8 nên ta sẽ có một vài byte là sai số.

## Ý tưởng phụ

Việc sử dụng 7 bit ta có thể biểu diễn 127 ký tự. Chúng ta chỉ cần 94 giá trị để biểu diễn bảng chữ cái và ký tự có nghĩa, và các ký tự điều khiển như `"\n", "\t"` .

Với file log, có rất nhiều từ được lặp đi lặp lại ví dụ như `null` mình có thể biểu diễn nó duy nhất bằng 1 byte với giá trị là `0x00` và nhiều từ khác nữa có thể tự custom trong quá trình phân tích.

## Ngoài lề

- Giảm memory cần thiết khi đọc file
    - Class File cung cấp độ dài file khi được gọi (object sẽ lấy thông tin của OS).
    - Có thể đọc file ngắt quãng tuần tự?