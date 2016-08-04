# ValidText
这是一个随机生成验证码的自定义控件。
效果图如下：
![alt text](https://raw.githubusercontent.com/sunflowerseat/ValidText/master/preview/validtext.png "Title")

使用方法
```
<com.fancy.validlib.ValidText
            android:id="@+id/valid1"
            android:layout_width="80dp"
            android:layout_height="40dp"
            android:textSize="20sp"
            android:background="@drawable/background"
            />
```
背景和普通控件一样设置，
textSize只是一个相对大小，最终控件会根据这个大小对每个字符进行随机缩放（0.8-1.2）
通过`validText.equalText(String s)`这个方法，可以判断一个字符串与验证码图片上的字符是否相等，忽略大小写
通过`validText.getText()`这个方法，可以得到验证码图片上的字符串。