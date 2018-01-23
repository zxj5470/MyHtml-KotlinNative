# MyHTML-KotlinNative

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
```
cat /etc/ld.so.conf
```
- append /usr/local/lib at the end:
```
include /etc/ld.so.conf.d/*.conf   # your default option
/usr/local/lib
```
- then type these
```
sudo ldconfig
```