package milan.hva.com.homework;
import android.database.Cursor;
import android.database.CursorWrapper;

public class HomeworkCursorWrapper extends CursorWrapper {

    public HomeworkCursorWrapper(Cursor cursor) {

        super(cursor);

    }

    public Homework getHomework() {
        long id = getLong(getColumnIndex(HomeworkDbSchema.HomeworkTable.Cols.ID));
        String name = getString(getColumnIndex(HomeworkDbSchema.HomeworkTable.Cols.NAME));
        String date = getString(getColumnIndex(HomeworkDbSchema.HomeworkTable.Cols.DATE));
        int isFinished = getInt(getColumnIndex(HomeworkDbSchema.HomeworkTable.Cols.FINISHED));
        String subject = getString(getColumnIndex(HomeworkDbSchema.HomeworkTable.Cols.SUBJECT));
        Homework homework = new Homework();
        homework.setId(id);
        homework.setName(name);
        homework.setDate(date);
        homework.setFinished(isFinished != 0);
        homework.setSubject(subject);
        return homework;

    }

}
