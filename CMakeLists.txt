cmake_minimum_required(VERSION 3.8)

set(CMAKE_MODULE_PATH ${CMAKE_CURRENT_LIST_DIR}/KotlinCMakeModule)

project(untitled Kotlin)

set(MYHTML_INCLUDE_DIR /usr/local/include)
set(MYHTML_LIBRARY "-lmyhtml")

# library
cinterop(
        NAME myhtml
        DEF_FILE src/c_interop/libmyhtml.def
        COMPILER_OPTS "-I ${MYHTML_INCLUDE_DIR}"
)

konanc_executable(
        NAME MyHtml-KotlinNative
        SOURCES src/kotlin/
        LIBRARIES myhtml
        LINKER_OPTS "${CURL_LIBRARY}"
)