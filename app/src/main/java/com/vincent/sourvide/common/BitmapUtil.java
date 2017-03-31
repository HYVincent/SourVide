// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package com.vincent.sourvide.common;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public class BitmapUtil
{


    private static int computeSampleSize(android.graphics.BitmapFactory.Options options, int i, int j)
    {
        int k1;
label0:
        {
            int k = options.outHeight;
            int l = options.outWidth;
            int i1 = 1;
            if (k > i || l > j)
            {
                int j1 = Math.round((float)k / (float)i);
                k1 = Math.round((float)l / (float)j);
                if (j1 > k1)
                    break label0;
                i1 = j1;
            }
            return i1;
        }
        return k1;
    }

    public static Bitmap createReflectedBitmap(Bitmap bitmap)
    {
        if (bitmap == null)
            return null;
        int i = bitmap.getWidth();
        int j = bitmap.getHeight();
        int k = bitmap.getWidth();
        int l = bitmap.getHeight() / 2;
        if (i == 0 || j == 0)
            return null;
        Matrix matrix = new Matrix();
        matrix.preScale(1.0F, -1F);
        Bitmap bitmap1;
        Bitmap bitmap2;
        Canvas canvas;
        Paint paint;
        try
        {
            bitmap1 = Bitmap.createBitmap(bitmap, 0, j / 2, i, j / 2, matrix, false);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        if (bitmap1 == null)
            return null;
        bitmap2 = Bitmap.createBitmap(k, 4 + (j + l), android.graphics.Bitmap.Config.ARGB_8888);
        if (bitmap2 == null)
            return null;
        canvas = new Canvas(bitmap2);
        canvas.drawBitmap(bitmap, 0.0F, 0.0F, null);
        canvas.drawBitmap(bitmap1, 0.0F, j + 4, null);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new LinearGradient(0.0F, j, 0.0F, 4 + bitmap2.getHeight(), 0x70ffffff, 0xffffff, android.graphics.Shader.TileMode.MIRROR));
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0F, j, i, 4 + bitmap2.getHeight(), paint);
        return bitmap2;
    }

    public static Bitmap drawableToBitmap(Drawable drawable)
    {
        if (drawable == null)
            return null;
        else
            return drawableToBitmap(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    public static Bitmap drawableToBitmap(Drawable drawable, int i, int j)
    {
        
        return null;
    }

    public static Bitmap getBitmapFromFile(File file)
    {
        return getBitmapFromFile(file, 0, 0);
    }

    public static Bitmap getBitmapFromFile(File file, int i)
    {
        if (file != null && file.exists())
        {
            android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getPath(), options);
            options.inSampleSize = i;
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(file.getPath(), options);
        } else
        {
            return null;
        }
    }

    public static Bitmap getBitmapFromFile(File file, int i, int j)
    {
        if (file != null && file.exists())
        {
            android.graphics.BitmapFactory.Options options = null;
            if (i > 0)
            {
                options = null;
                if (j > 0)
                {
                    options = new android.graphics.BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file.getPath(), options);
                    options.inSampleSize = computeSampleSize(options, i, j);
                    options.inJustDecodeBounds = false;
                }
            }
            return BitmapFactory.decodeFile(file.getPath(), options);
        } else
        {
            return null;
        }
    }

    public static String getPath(Context context, Uri uri)
    {
        String as[] = {
            "_data"
        };
        Cursor cursor = ((Activity)context).managedQuery(uri, as, null, null, null);
        int i = cursor.getColumnIndexOrThrow("_data");
        cursor.moveToFirst();
        return cursor.getString(i);
    }

    public static final Bitmap toBitmapGray(Bitmap bitmap)
    {
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint();
        ColorMatrix colormatrix = new ColorMatrix();
        colormatrix.setSaturation(0.0F);
        paint.setColorFilter(new ColorMatrixColorFilter(colormatrix));
        canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint);
        return bitmap1;
    }

    public static Bitmap toImageCircle(Bitmap bitmap)
    {
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectf = new RectF(rect);
        float f = bitmap.getWidth() / 2;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0xff424242);
        canvas.drawRoundRect(rectf, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmap1;
    }

    public static Bitmap toRoundCorner(Bitmap bitmap)
    {
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectf = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0xff424242);
        canvas.drawRoundRect(rectf, 10F, 10F, paint);
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmap1;
    }
}
