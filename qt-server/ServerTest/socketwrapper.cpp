#include "socketwrapper.h"

SocketWrapper::SocketWrapper(QTcpSocket *socket)
{
    this->socket = socket;

    connect(socket, SIGNAL(disconnected()), this, SLOT(disconnect()));
    connect(socket, SIGNAL(readyRead()), this, SLOT(readyRead()));

    socket->write("hello client\r\n");
    socket->flush();
}

void SocketWrapper::disconnect()
{
    qDebug() << "Host disconnected!";
    socket->close();
}

void SocketWrapper::readyRead()
{
    QByteArray readData= socket->readAll();
    QString test = QString(readData).trimmed();

    qDebug() << "Data readyToRead!";
    qDebug() << "Data: " + test;


    socket->write("OK!\r\n");
    socket->flush();

    QJsonObject obj = parseToQJson(test);
}

QJsonObject SocketWrapper::parseToQJson(const QString& in)
{
    QJsonObject obj;
    QJsonDocument doc = QJsonDocument::fromJson(in.toUtf8());

    // check validity of the document
    if(!doc.isNull())
    {
        if(doc.isObject())
        {
            obj = doc.object();
        }
        else
        {
            qDebug() << "Document is not an object" << endl;
        }
    }
    else
    {
        qDebug() << "Invalid JSON...\n" << in << endl;
    }

    return obj;
}

