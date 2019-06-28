/*
package com.mashup.tenSecond.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.namget.diaryLee.data.db.dao.EventIconDao
import com.namget.diaryLee.data.db.dao.WriteDao
import com.namget.diaryLee.data.db.entity.EventIconEntity
import com.namget.diaryLee.data.db.entity.WriteDataEntity
import com.namget.diaryLee.util.Event

@Database(entities = arrayOf(EventIconEntity::class, WriteDataEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun EventIconDao(): EventIconDao
    abstract fun WriteDao(): WriteDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context?): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context!!.applicationContext, AppDatabase::class.java, "diaryLee.db").build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            if (!(INSTANCE?.inTransaction() ?: true)) {
                INSTANCE = null
            }
        }
    }


    */
/**
     *google aac sample code
     *sInstance = buildDatabase(context.getApplicationContext(), executors);
     *sInstance.updateDatabaseCreated(context.getApplicationContext());
     * 위와같이 처음 생성해야하는 데이터베이스를 가져온다.
     *//*



*/
/* *//*

    */
/**
     * sample code
     * Build the database. [Builder.build] only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     *//*
*/
/*
    private fun buildDatabase(appContext: Context,
                              executors: AppExecutors): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        executors.diskIO().execute({
                            // Add a delay to simulate a long-running operation
                            addDelay()
                            // Generate the data for pre-population
                            val database = AppDatabase.getInstance(appContext, executors)
                            val products = DataGenerator.generateProducts()
                            val comments = DataGenerator.generateCommentsForProducts(products)

                            insertData(database, products, comments)
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated()
                        })
                    }
                })
                .addMigrations(MIGRATION_1_2)
                .build()
    }

    *//*

    */
/**
     * Check whether the database already exists and expose it via [.getDatabaseCreated]
     *//*
*/
/*
    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated()
        }
    }

    private fun setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }

    private fun insertData(database: AppDatabase, products: List<ProductEntity>,
                           comments: List<CommentEntity>) {
        database.runInTransaction {
            database.productDao().insertAll(products)
            database.commentDao().insertAll(comments)
        }
    }

    private fun addDelay() {
        try {
            Thread.sleep(4000)
        } catch (ignored: InterruptedException) {
        }

    }

    fun getDatabaseCreated(): LiveData<Boolean> {
        return mIsDatabaseCreated
    }

    private val MIGRATION_1_2 = object : Migration(1, 2) {

        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `productsFts` USING FTS4(" + "`name` TEXT, `description` TEXT, content=`products`)")
            database.execSQL("INSERT INTO productsFts (`rowid`, `name`, `description`) " + "SELECT `id`, `name`, `description` FROM products")

        }
    }*//*



}*/
