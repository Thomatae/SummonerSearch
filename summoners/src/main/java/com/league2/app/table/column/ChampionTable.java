package com.league2.app.table.column;

import android.provider.BaseColumns;
import com.league2.app.table.DatabaseTable;

public final class ChampionTable extends DatabaseTable {

    public static final String TABLE_NAME = "decks";

    public static final class Columns implements BaseColumns {
        public static final TableColumn COLUMN_NAME = new TableColumn("name", ColumnType.TEXT, true);
        public static final TableColumn COLUMN_DECK_CLASS = new TableColumn("deckClass", ColumnType.TEXT, true);
        public static final TableColumn COLUMN_WINS = new TableColumn("wins", ColumnType.INTEGER, false, "0");
        public static final TableColumn COLUMN_LOSSES = new TableColumn("losses", ColumnType.INTEGER, false, "0");
    }

    public static final String getCreateQuery() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
               Columns._ID + " INTEGER PRIMARY KEY, " +
               Columns.COLUMN_NAME + ", " +
               Columns.COLUMN_DECK_CLASS + ", " +
               Columns.COLUMN_WINS + ", " +
               Columns.COLUMN_LOSSES + ")";
    }

    public static final String getDeleteQuery() {
        return "DELETE TABLE IF EXISTS " + TABLE_NAME;
    }
}
