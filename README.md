# MyHTML-KotlinNative
> Transform C lib MYHTML to KotlinNative

Powered-By
[lexborisov/myhtml](https://github.com/lexborisov/myhtml)

## Platform 
- Mac OS X Sierra 10.12.6
- Deepin Linux 15.5 (Base on Debian 8)

## Preparation
### Install `myhtml`
```bash
git clone https://github.com/lexborisov/myhtml
cd myhtml
mkdir build
cd build
cmake ../
make 
make install
``` 

## Misc
### for Linux
if your libs were installed in /usr/local/lib (Makefile default) then do these as follows:
```bash
cat /etc/ld.so.conf
```
- append /usr/local/lib at the end:
```bash
include /etc/ld.so.conf.d/*.conf   # your default option
/usr/local/lib
```
- then type these
```bash
sudo ldconfig
```

## Usage 
> Be careful at `CPointer<ByteVar>`.
> use `toKString` instead of `toString`

```kotlin
import kotlinx.cinterop.*
import libmyhtml.*
import platform.posix.strlen

fun main(args: Array<String>) {
    val html = "<div><span>HTML</span></div>"
    val myhtml = myhtml_create()
    myhtml_init(myhtml, MyHTML_OPTIONS_DEFAULT, 1, 0)

    val tree = myhtml_tree_create()
    myhtml_tree_init(tree, myhtml)
    myhtml_parse(tree, MyENCODING_UTF_8, html, strlen(html))
    memScoped {
        val str = alloc<mycore_string_raw>()
        myhtml_serialization_tree_buffer(myhtml_tree_get_document(tree), str.ptr)

        //toKString is correct.Using `toString` will return the address of CPointer<ByteVar>
        val data=str.data!!.toKString()
        println(data)

        mycore_string_raw_destroy(str.ptr, false)
        myhtml_tree_destroy(tree)
        myhtml_destroy(myhtml)
    }
}
```