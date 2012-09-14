/*
 * Copyright 2004-present Facebook. All Rights Reserved.
 */
package com.facebook.presto.block;

import com.facebook.presto.block.uncompressed.UncompressedBlock;
import com.google.common.primitives.Longs;
import org.testng.annotations.Test;

import static com.facebook.presto.block.BlockCursorAssertions.assertNextValue;
import static org.testng.Assert.assertFalse;

public class TestMaskedBlock
{
    @Test
    public void test()
            throws Exception
    {
        UncompressedBlock uncompressed = Blocks.createBlock(0, "a", "b", "c", "d", "e", "f");

        MaskedBlock block = new MaskedBlock(uncompressed, Longs.asList(0, 2, 4));

        Cursor cursor = block.blockCursor();

        assertNextValue(cursor, 0, "a");
        assertNextValue(cursor, 2, "c");
        assertNextValue(cursor, 4, "e");

        assertFalse(cursor.advanceNextPosition());
    }
}
