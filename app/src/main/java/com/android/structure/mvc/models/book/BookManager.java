package com.android.structure.mvc.models.book;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.android.structure.mvc.commons.BackgroundThreadPoster;
import com.android.structure.mvc.commons.BaseObservableManager;
import com.android.structure.mvc.commons.MainThreadPoster;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates the logic related to Books, including interactions with MVC model.
 */
public class BookManager extends BaseObservableManager<BookManager.BookManagerListener> {

    /*
     * MVC model of the app is a database of Books stored on the device. The model is accessed
     * via a non-standard ContentProvider having CONTENT_URI "content://book/inbox".
     */
    private static final String CONTENT_URI = "content://sms/inbox";

    // The "data columns" used in our app
    private static final String[] COLUMNS_OF_INTEREST = new String[] {
            "_id",
            "address",
            "date",
            "body",
            "read"
    };

    // Default sort order by descending date
    private static final String DEFAULT_SORT_ORDER = "date DESC";


    /**
     * Classes implementing this interface can be registered as callbacks with
     * {@link BookManager}
     */
    public interface BookManagerListener {
        /**
         * This method will be called on UI thread when fetching of requested Books
         * completes.
         * @param books a list of fetched Books; will never be null
         */
        void onBooksFetched(List<Book> books);
    }


    private ContentResolver mContentResolver;
    private final MainThreadPoster mMainThreadPoster;
    private final BackgroundThreadPoster mBackgroundThreadPoster;

    public BookManager(ContentResolver contentResolver,
                       MainThreadPoster mainThreadPoster,
                       BackgroundThreadPoster backgroundThreadPoster) {
        mContentResolver = contentResolver;
        mMainThreadPoster = mainThreadPoster;
        mBackgroundThreadPoster = backgroundThreadPoster;
    }

    /**
     * Fetch an Book by its ID. Fetch will be done on background thread and registered
     * listeners will be notified of result on UI thread.
     * @param id ID of book to fetch
     */
    public void fetchBookById(final long id) {
        mBackgroundThreadPoster.post(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = null;
                try {
                    cursor = mContentResolver.query(
                            ContentUris.withAppendedId(Uri.parse(CONTENT_URI), id),
                            COLUMNS_OF_INTEREST,
                            null,
                            null,
                            DEFAULT_SORT_ORDER
                    );

                    List<Book> result = extractBooksFromCursor(cursor);
                    notifyBooksFetched(result);
                } finally {
                    if (cursor != null) cursor.close();
                }
            }
        });
    }


    /**
     * Fetch all Books. Fetch will be done on background thread and registered
     * listeners will be notified of result on UI thread.
     */
    public void fetchAllBooks() {
        mBackgroundThreadPoster.post(new Runnable() {
            @Override
            public void run() {
                fetchAllBooksSync();
            }
        });
    }

    private void fetchAllBooksSync() {
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(
                    Uri.parse(CONTENT_URI),
                    COLUMNS_OF_INTEREST,
                    null,
                    null,
                    DEFAULT_SORT_ORDER
            );

            List<Book> result = extractBooksFromCursor(cursor);
            notifyBooksFetched(result);
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    private List<Book> extractBooksFromCursor(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            List<Book> result = new ArrayList<>(cursor.getCount());
            do {
                result.add(new Book(
                        cursor.getLong(cursor.getColumnIndexOrThrow("_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("address")),
                        cursor.getString(cursor.getColumnIndexOrThrow("body")),
                        cursor.getString(cursor.getColumnIndexOrThrow("date")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("read")) == 0));
            } while (cursor.moveToNext());
            return result;
        } else {
            return new ArrayList<>(0);
        }
    }

    /**
     * Mark specific Book as read.
     * @param id ID of book to be marked as read.
     */
    public void markBookAsRead(final long id) {
        mBackgroundThreadPoster.post(new Runnable() {
            @Override
            public void run() {
                // Designating the fields that should be updated
                ContentValues values = new ContentValues();
                values.put("read", true);

                mContentResolver.update(
                        ContentUris.withAppendedId(Uri.parse(CONTENT_URI), id),
                        values,
                        null,
                        null);

                // re-fetch all books and notify listeners
                fetchAllBooksSync();
            }
        });

    }

    private void notifyBooksFetched(final List<Book> books) {
        mMainThreadPoster.post(new Runnable() {
            @Override
            public void run() {
                for (BookManagerListener listener : getListeners()) {
                    listener.onBooksFetched(books);
                }
            }
        });
    }

}
