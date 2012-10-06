import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
* Created by IntelliJ IDEA.
* User: VAIO
* Date: 20.08.12
* Time: 0:57
* To change this template use File | Settings | File Templates.
*/
public class MainTest {
    @Before
    public void setUp() throws Exception {

        Main.Desk = new int[8][8];

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCheck() throws Exception {

    }

    @Test
    public void testCheckVrt() throws Exception {
        assertTrue(Main.CheckVrt());

        Main.Desk[1][1] = 1;
        assertTrue(Main.CheckVrt());

        Main.Desk[3][1] = 1;
        assertFalse(Main.CheckVrt());

        Main.Desk[3][1] = 0;
        Main.Desk[5][6] = 1;
        assertTrue(Main.CheckVrt());

        Main.Desk[0][0] = 1;
        Main.Desk[7][0] = 1;
        assertFalse(Main.CheckVrt());
    }

    @Test
    public void testCheckDgl_L_main() throws Exception {
        assertTrue(Main.CheckDgl());

        Main.Desk[1][1] = 1;
        assertTrue(Main.CheckDgl());

        Main.Desk[2][2] = 1;
        assertFalse(Main.CheckDgl());

        Main.Desk[1][1] = 0;
        Main.Desk[2][2] = 0;
        Main.Desk[4][4] = 2;
        Main.Desk[6][6] = 4;
        assertFalse(Main.CheckDgl());
    }

    @Test
    public void testCheckDgl_R_main() throws Exception {
        assertTrue(Main.CheckDgl());

        Main.Desk[6][1] = 1;
        assertTrue(Main.CheckDgl());

        Main.Desk[5][2] = 1;
        assertFalse(Main.CheckDgl());

        Main.Desk[6][1] = 0;
        Main.Desk[5][2] = 0;
        Main.Desk[4][3] = 2;
        Main.Desk[1][6] = 4;
        assertFalse(Main.CheckDgl());
    }

    @Test
    public void testCheckDgl_left() throws Exception {
        Main.Desk[2][1] = 1;
        assertTrue(Main.CheckDgl());

        Main.Desk[3][2] = 1;
        assertFalse(Main.CheckDgl());

        Main.Desk[3][2] = 0;
        Main.Desk[3][4] = 1;
        assertTrue(Main.CheckDgl());
    }

    @Test
    public void testCheckDgl_right() throws Exception {
        Main.Desk[3][5] = 1;
        assertTrue(Main.CheckDgl());

        Main.Desk[4][4] = 1;
        assertFalse(Main.CheckDgl());

        //Main.Desk[4][4] = 0;
        //Main.Desk[3][4] = 1;
        //assertTrue(Main.CheckDgl());
    }


    @Test
    public void testGetTopLine() throws Exception {
        assertEquals(-1, Main.GetTopLine());
        Main.Desk[0][3] = 1;
        assertEquals(0, Main.GetTopLine());
        Main.Desk[1][5] = 5;
        assertEquals(1, Main.GetTopLine());
        Main.Desk[2][4] = 1;
        assertEquals(2, Main.GetTopLine());
    }

    @Test
    public void testGetTopCol() throws Exception {
        assertEquals(-1, Main.GetTopLine());
        Main.Desk[0][3] = 1;
        Main.Desk[1][5] = 4;
        Main.Desk[2][4] = 2;
        assertEquals(3, Main.GetTopCol(0));
        assertEquals(5, Main.GetTopCol(1));
        assertEquals(4, Main.GetTopCol(2));
    }


    @Test
    public void testGoLeftUp() throws Exception {
        Main.Desk[0][0] = 1;
        assertTrue(Main.GoLeftUp());
        assertEquals(Main.Desk[1][0],1);
        assertTrue(Main.GoLeftUp());
        assertEquals(Main.Desk[2][0],1);
        Main.Desk[2][0] = 3;
        assertTrue(Main.GoLeftUp());
        assertEquals(Main.Desk[3][2],1);
        Main.Desk[3][2] = 5;
        assertTrue(Main.GoLeftUp());
        assertEquals(Main.Desk[4][4],1);
        Main.Desk[4][4] = 9;
        assertFalse(Main.GoLeftUp());
        Main.Desk[4][4]=1;
        assertTrue(Main.GoLeftUp());
        assertEquals(Main.Desk[5][0],1);
        assertTrue(Main.GoLeftUp());
        assertEquals(Main.Desk[6][0],1);
        assertTrue(Main.GoLeftUp());
        assertEquals(Main.Desk[7][0],1);
        assertFalse(Main.GoLeftUp());
    }

    @Test
    public void testGoDown() throws Exception {
        Main.Desk[0][1] = 6;
        Main.Desk[1][3] = 1;
        Main.Desk[2][6] = 5;
        Main.Desk[3][2] = 2;
        Main.Desk[4][4] = 3;
        Main.Desk[5][7] = 7;
        Main.Desk[6][0] = 3;
        Main.Desk[7][3] = 1;

        Main.GoDown();
        assertEquals(Main.Desk[7][3], 0);
        assertEquals(Main.Desk[6][0], 4);

        Main.GoDown();
        assertEquals(Main.Desk[6][0], 0);
        assertEquals(Main.Desk[5][7], 8);

        Main.GoDown();
        assertEquals(Main.Desk[5][5], 0);
        assertEquals(Main.Desk[4][4], 4);
    }

}
