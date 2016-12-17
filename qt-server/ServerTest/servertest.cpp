#include "servertest.h"

ServerTest::ServerTest(QObject *parent) : QObject(parent) {
    server = new QTcpServer(this);

    connect(server, SIGNAL(newConnection()), this, SLOT(newConnection()));

    if(!server->listen(QHostAddress::Any, 1234)) {
        qDebug() << "Server could not start!";
    } else {
        qDebug() << "Server started!";
    }
}

void ServerTest::newConnection() {
    QTcpSocket *socket = server->nextPendingConnection();
    new SocketWrapper(socket);
}
