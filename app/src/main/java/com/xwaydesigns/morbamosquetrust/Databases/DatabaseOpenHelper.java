package com.xwaydesigns.morbamosquetrust.Databases;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper
{
  private static final String DATABASE_NAME = "morba_database.db";
  private static final int DATABASE_VERSION=1;

  public DatabaseOpenHelper(Context ctx)
  {
      super(ctx,DATABASE_NAME,null,DATABASE_VERSION);
  }
}
