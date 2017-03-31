// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package com.vincent.sourvide.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;

public class FileUtils
{


    public static boolean checkFileExist(String s)
    {
        return (new File(s)).exists();
    }

    public static void copyAssetData(Context context, String s, String s1, String s2)
    {
        
    }

    public static void copyAssetFolderToSd(Context context, String s, String s1)
    {
        
    }

    public static void copyDirectiory(String s, String s1)
        throws IOException
    {
        (new File(s1)).mkdirs();
        File afile[] = (new File(s)).listFiles();
        int i = 0;
        do
        {
            if (i >= afile.length)
                return;
            if (afile[i].isFile())
                copyFile(afile[i], new File((new StringBuilder(String.valueOf((new File(s1)).getAbsolutePath()))).append(File.separator).append(afile[i].getName()).toString()));
            if (afile[i].isDirectory())
                copyDirectiory((new StringBuilder(String.valueOf(s))).append("/").append(afile[i].getName()).toString(), (new StringBuilder(String.valueOf(s1))).append("/").append(afile[i].getName()).toString());
            i++;
        } while (true);
    }

    public static final boolean copyFile(File file, File file1)
    {
        return false;
    }

    public static final void copyFileFolder(String s, String s1)
    {
        (new File(s1)).mkdirs();
        File afile[] = (new File(s)).listFiles();
        if (afile != null)
        {
            for (int i = 0; i < afile.length; i++)
                if (afile[i].isFile())
                    copyFile(afile[i], new File((new StringBuilder(String.valueOf((new File(s1)).getAbsolutePath()))).append(File.separator).append(afile[i].getName()).toString()));

        }
    }

    public static void deleteFile(File file)
    {
        if (file.exists())
            if (file.isFile())
                file.delete();
            else
            if (file.isDirectory())
            {
                File afile[] = file.listFiles();
                for (int i = 0; i < afile.length; i++)
                    deleteFile(afile[i]);

            }
    }

    public static byte[] getCodeByFilePath(String s)
    {

        return null;
        
    }

    public static String getFileNameByPath(String s)
    {
        return s.substring(1 + s.lastIndexOf("/"));
    }

    public static String getFileNameByUrl(String s)
    {
        return s.substring(1 + s.lastIndexOf("/"));
    }

    public static final String getStringByFile(String s)
    {
        
        return null;
    }

    public static final void saveBitmapToFile(Bitmap bitmap, String s)
    {
        
    }

    public static void saveBytesToFile(byte abyte0[], String s, String s1)
    {
       
    }

    public static final boolean saveBytesToFile(byte abyte0[], File file)
    {
       return false;
    }

    public static final void saveStringToFile(String s, String s1)
    {
        File file = new File(s1);
        try
        {
            FileWriter filewriter = new FileWriter(file);
            filewriter.write(s);
            filewriter.flush();
            filewriter.close();
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public static byte[] stringToByte(String s)
    {
        int i = 0;
        int j = 0;
        int k = 0 + s.length() / 2;
        byte abyte0[] = new byte[k];
        int l = 0;
        do
        {
            if (l >= k)
                return abyte0;
            abyte0[l] = (byte)Integer.parseInt(s.substring(j, j + 2), 16);
            j += 2;
            i++;
            l++;
        } while (true);
    }
}
