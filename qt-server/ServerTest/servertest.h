#ifndef SERVERTEST_H
#define SERVERTEST_H

#include <QObject>
#include <QDebug>
#include <QTcpServer>
#include <QTcpSocket>

#include "socketwrapper.h"

class ServerTest : public QObject
{
    Q_OBJECT

    QTcpServer *server;

public:
    explicit ServerTest(QObject *parent = 0);

signals:

public slots:
    void newConnection();
};

#endif // SERVERTEST_H
