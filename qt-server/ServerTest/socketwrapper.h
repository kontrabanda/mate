#ifndef SOCKETWRAPPER_H
#define SOCKETWRAPPER_H

#include <QObject>
#include <QTcpSocket>
#include <QDebug>
#include <QJsonObject>
#include <QJsonDocument>

class SocketWrapper : public QObject
{
    Q_OBJECT
    QTcpSocket *socket;

    SocketWrapper(){}
    QJsonObject parseToQJson(const QString& in);

public:
    SocketWrapper(QTcpSocket *socket);

signals:

public slots:
    void disconnect();
    void readyRead();
};

#endif // SOCKETWRAPPER_H
