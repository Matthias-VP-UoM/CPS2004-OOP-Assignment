cmake_minimum_required(VERSION 3.0.0)
project(task1b VERSION 0.1.0 LANGUAGES C CXX)

set(CMAKE_CXX_STANDARD 20)

include(CTest)
enable_testing()

set(SOURCE_FILES1 main.cpp Product.cpp ComputerGame.cpp Clothing.cpp Electronics.cpp Book.cpp Transport.cpp Aircraft.cpp SeaVessel.cpp Truck.cpp Packaging.cpp Stock.cpp StockItem.cpp Shipment.cpp AppState.cpp Customer.cpp)
add_executable(task1bapp ${SOURCE_FILES1})

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
