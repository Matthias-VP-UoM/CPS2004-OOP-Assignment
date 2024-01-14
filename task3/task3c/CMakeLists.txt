cmake_minimum_required(VERSION 3.0.0)
project(task3c VERSION 0.1.0 LANGUAGES C CXX)

set(CMAKE_CXX_STANDARD 20)

include(CTest)
enable_testing()

add_executable(task3c main.cpp Product.cpp Book.cpp ComputerGame.cpp Clothing.cpp Electronics.cpp Stock.cpp StockItem.cpp)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
