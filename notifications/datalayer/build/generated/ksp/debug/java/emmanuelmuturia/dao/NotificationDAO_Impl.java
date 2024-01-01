package emmanuelmuturia.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import emmanuelmuturia.entity.NotificationEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class NotificationDAO_Impl implements NotificationDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NotificationEntity> __insertionAdapterOfNotificationEntity;

  private final EntityDeletionOrUpdateAdapter<NotificationEntity> __deletionAdapterOfNotificationEntity;

  public NotificationDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNotificationEntity = new EntityInsertionAdapter<NotificationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `notifications` (`notificationId`,`notificationTitle`,`notificationBody`,`notificationTimestamp`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final NotificationEntity entity) {
        statement.bindString(1, entity.getNotificationId());
        if (entity.getNotificationTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNotificationTitle());
        }
        if (entity.getNotificationBody() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getNotificationBody());
        }
        statement.bindLong(4, entity.getNotificationTimestamp());
      }
    };
    this.__deletionAdapterOfNotificationEntity = new EntityDeletionOrUpdateAdapter<NotificationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `notifications` WHERE `notificationId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final NotificationEntity entity) {
        statement.bindString(1, entity.getNotificationId());
      }
    };
  }

  @Override
  public Object addNotification(final NotificationEntity notificationEntity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNotificationEntity.insert(notificationEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteNotification(final NotificationEntity notificationEntity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfNotificationEntity.handle(notificationEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllNotifications(
      final Continuation<? super List<NotificationEntity>> $completion) {
    final String _sql = "SELECT * FROM notifications";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<NotificationEntity>>() {
      @Override
      @NonNull
      public List<NotificationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNotificationId = CursorUtil.getColumnIndexOrThrow(_cursor, "notificationId");
          final int _cursorIndexOfNotificationTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "notificationTitle");
          final int _cursorIndexOfNotificationBody = CursorUtil.getColumnIndexOrThrow(_cursor, "notificationBody");
          final int _cursorIndexOfNotificationTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "notificationTimestamp");
          final List<NotificationEntity> _result = new ArrayList<NotificationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NotificationEntity _item;
            final String _tmpNotificationId;
            _tmpNotificationId = _cursor.getString(_cursorIndexOfNotificationId);
            final String _tmpNotificationTitle;
            if (_cursor.isNull(_cursorIndexOfNotificationTitle)) {
              _tmpNotificationTitle = null;
            } else {
              _tmpNotificationTitle = _cursor.getString(_cursorIndexOfNotificationTitle);
            }
            final String _tmpNotificationBody;
            if (_cursor.isNull(_cursorIndexOfNotificationBody)) {
              _tmpNotificationBody = null;
            } else {
              _tmpNotificationBody = _cursor.getString(_cursorIndexOfNotificationBody);
            }
            final long _tmpNotificationTimestamp;
            _tmpNotificationTimestamp = _cursor.getLong(_cursorIndexOfNotificationTimestamp);
            _item = new NotificationEntity(_tmpNotificationId,_tmpNotificationTitle,_tmpNotificationBody,_tmpNotificationTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
