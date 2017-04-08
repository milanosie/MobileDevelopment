package milan.hva.com.homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HomeworkDataSource {

    private SQLiteDatabase mDatabase;

    public HomeworkDataSource(Context context) {

        mDatabase = new HomeworkBaseHelper(context).getWritableDatabase();

    }

    private static ContentValues getContentValues(Homework homework) {

        ContentValues values = new ContentValues();
        values.put(HomeworkDbSchema.HomeworkTable.Cols.NAME, homework.getName());
        values.put(HomeworkDbSchema.HomeworkTable.Cols.DATE, homework.getDate());
        values.put(HomeworkDbSchema.HomeworkTable.Cols.FINISHED, homework.isFinished());
        values.put(HomeworkDbSchema.HomeworkTable.Cols.SUBJECT, homework.getSubject());

        return values;
    }

    public void addHomework(Homework homework) {
        ContentValues values = getContentValues(homework);
        mDatabase.insert(HomeworkDbSchema.HomeworkTable.NAME, null, values);

    }

    public void updateHomework(Homework homework) {

        String idString = Long.toString(homework.getId());
        ContentValues values = getContentValues(homework);
        mDatabase.update(HomeworkDbSchema.HomeworkTable.NAME,
                values,
                HomeworkDbSchema.HomeworkTable.Cols.ID + " = ?",
                new String[]{idString});
    }

    private HomeworkCursorWrapper queryHomework(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                HomeworkDbSchema.HomeworkTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new HomeworkCursorWrapper(cursor);
    }

    public Cursor getAllHomework() {
        return queryHomework(null, null);
    }

    public Homework getHomeworkById(long id) {

        HomeworkCursorWrapper cursor = queryHomework(HomeworkDbSchema.HomeworkTable.Cols.ID + " = ?",
                new String[]{Long.toString(id)});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getHomework();
        } finally {
            cursor.close();
        }
    }

    public void deleteHomework(long id) {
        String idString = Long.toString(id);
        mDatabase.delete(HomeworkDbSchema.HomeworkTable.NAME,
                HomeworkDbSchema.HomeworkTable.Cols.ID + " = ?",
                new String[]{idString});
    }


}