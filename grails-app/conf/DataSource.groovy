/*dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}*/
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    //cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
}

// environment specific settings
environments {
    development {

        dataSource_trans {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = "org.hibernate.dialect.PostgreSQLDialect"
            url = "jdbc:postgresql://localhost:5432/freeAppListing"
            username = "postgres"
            password = "qtagtech"
            logSql = true
        }

        dataSource {
            grails {
                mongo {
                    host = "localhost"
                    port = 27017
                    username = ""
                    password=""
                    databaseName = "freeapplisting"
                    options {
                        autoConnectRetry = true
                        connectTimeout = 300
                        connectionsPerHost = 10 // The maximum number of connections allowed per host
                        threadsAllowedToBlockForConnectionMultiplier = 5
                        maxWaitTime = 120000 // Max wait time of a blocking thread for a connection.
                        socketTimeout = 0 // The socket timeout. 0 == infinite
                        socketKeepAlive = false // Whether or not to have socket keep alive turned on
                        writeNumber = 0 // This specifies the number of servers to wait for on the write operation
                        writeTimeout = 0 // The timeout for write operations in milliseconds
                        writeFsync = false // Whether or not to fsync
                        maxAutoConnectRetryTime = 0 // The maximum amount of time in millisecons to spend retrying
                        slaveOk = false // Specifies if the driver is allowed to read from secondaries or slaves
                        ssl = false // Specifies if the driver should use an SSL connection to Mongo
                        //sslSocketFactory = … // Specifies the SSLSocketFactory to use for creating SSL connections
                    }
                }
            }
        }
    }
    test {

        dataSource_trans {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = "net.kaleidos.hibernate.PostgresqlExtensionsDialect"
            url = "jdbc:postgresql://localhost:5432/freeAppListing"
            username = "postgres"
            password = "qtagtech"
        }

        dataSource {
            grails {
                mongo {
                    host = "localhost"
                    port = 27017
                    username = "freeAppDb"
                    password = "123456"
                    databaseName = "FreeApps"
                    options {
                        autoConnectRetry = true
                        connectTimeout = 300
                        connectionsPerHost = 10 // The maximum number of connections allowed per host
                        threadsAllowedToBlockForConnectionMultiplier = 5
                        maxWaitTime = 120000 // Max wait time of a blocking thread for a connection.
                        socketTimeout = 0 // The socket timeout. 0 == infinite
                        socketKeepAlive = false // Whether or not to have socket keep alive turned on
                        writeNumber = 0 // This specifies the number of servers to wait for on the write operation
                        writeTimeout = 0 // The timeout for write operations in milliseconds
                        writeFsync = false // Whether or not to fsync
                        maxAutoConnectRetryTime = 0 // The maximum amount of time in millisecons to spend retrying
                        slaveOk = false // Specifies if the driver is allowed to read from secondaries or slaves
                        ssl = false // Specifies if the driver should use an SSL connection to Mongo
                        //sslSocketFactory = … // Specifies the SSLSocketFactory to use for creating SSL connections
                    }
                }
            }
        }
    }
    production {
        grails {
            mongo {
                host = System.env.OPENSHIFT_MONGODB_DB_HOST
                port = System.env.OPENSHIFT_MONGODB_DB_PORT
                username = "admin"
                password="KqEBAJ_Z2e3S"
                databaseName = "fal"
            }
        }
        dataSource_trans {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = "org.hibernate.dialect.PostgreSQLDialect"
            host = System.env.OPENSHIFT_POSTGRESQL_DB_HOST
            port = System.env.OPENSHIFT_POSTGRESQL_DB_PORT
            url = "jdbc:postgresql://"+host+":"+port+"/"+System.env.OPENSHIFT_APP_NAME
            username = System.env.OPENSHIFT_POSTGRESQL_DB_USERNAME
            password = System.env.OPENSHIFT_POSTGRESQL_DB_PASSWORD
        }
    }
}
