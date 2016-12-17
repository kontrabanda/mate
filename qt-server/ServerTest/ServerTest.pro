QT += core
QT += network
QT -= gui

CONFIG += c++11

TARGET = ServerTest
CONFIG += console
CONFIG -= app_bundle

TEMPLATE = app

SOURCES += main.cpp \
    servertest.cpp \
    socketwrapper.cpp

HEADERS += \
    servertest.h \
    socketwrapper.h
