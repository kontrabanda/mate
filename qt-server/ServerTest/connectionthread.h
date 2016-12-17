#ifndef CONNECTIONTHREAD_H
#define CONNECTIONTHREAD_H

#include <QObject>
#include <QThread>
#include <QDebug>

class ConnectionThread : public QThread
{
public:
    ConnectionThread();
    void run();};

#endif // CONNECTIONTHREAD_H
