#include <QCoreApplication>
#include "servertest.h"

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    ServerTest tServer;
    return a.exec();
}
