package io.crowdcode.vehicle.security.filter;

import static org.junit.Assert.assertEquals;
import io.crowdcode.vehicle.security.filter.SortOrder;
import io.crowdcode.vehicle.security.filter.UserColumn;
import io.crowdcode.vehicle.security.filter.UserFilterParameters;
import io.crowdcode.vehicle.security.filter.UserFilterParameters.ColumnEntry;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UserFilterParametersTest {

    private UserFilterParameters params;
    
    @Before
    public void setUp() {
        params = new UserFilterParameters();
    }
    
    @Test
    public void testOrderToggling() throws Exception {
        ColumnEntry column = params.getColumn(UserColumn.SURENAME);
        assertEquals(SortOrder.NONE, column.getSortOrder());
        assertEquals(-1, column.getSortPosition());
        column.toggleOrder();
        assertEquals(SortOrder.ASCENDING, column.getSortOrder());
        assertEquals(0, column.getSortPosition());
        column.toggleOrder();
        assertEquals(SortOrder.DESCENDING, column.getSortOrder());
        assertEquals(0, column.getSortPosition());
        column.toggleOrder();
        assertEquals(SortOrder.NONE, column.getSortOrder());
        assertEquals(-1, column.getSortPosition());
    }
    
    @Test
    public void testSortedColumns() throws Exception {
        ColumnEntry username = params.getColumn(UserColumn.USERNAME);
        ColumnEntry surename = params.getColumn(UserColumn.SURENAME);
        ColumnEntry email = params.getColumn(UserColumn.EMAIL);

        username.toggleOrder();
        surename.toggleOrder();
        email.toggleOrder();
        
        List<ColumnEntry> entries = params.getSortedColumns();
        assertEquals(3, entries.size());
        
        assertEquals(username, entries.get(0));
        assertEquals(surename, entries.get(1));
        assertEquals(email, entries.get(2));
    }

    @Test
    public void testSortedColumnsAfterRemovingSortColumn() throws Exception {
        ColumnEntry username = params.getColumn(UserColumn.USERNAME);
        ColumnEntry surename = params.getColumn(UserColumn.SURENAME);
        ColumnEntry email = params.getColumn(UserColumn.EMAIL);
        
        username.toggleOrder(); // ASCENDING
        surename.toggleOrder(); // ASCENDING
        email.toggleOrder(); // ASCENDING
        surename.toggleOrder(); // DESCENDING
        surename.toggleOrder(); // NONE
        
        List<ColumnEntry> entries = params.getSortedColumns();
        assertEquals(2, entries.size());
        
        assertEquals(username, entries.get(0));
        assertEquals(email, entries.get(1));
    }
    
    @Test
    public void testSortOrderPosition() throws Exception {
        ColumnEntry username = params.getColumn(UserColumn.USERNAME);
        ColumnEntry surename = params.getColumn(UserColumn.SURENAME);
        ColumnEntry email = params.getColumn(UserColumn.EMAIL);
        assertEquals(-1, username.getSortPosition());
        assertEquals(-1, surename.getSortPosition());
        assertEquals(-1, email.getSortPosition());
        username.toggleOrder(); // ASCENDING
        assertEquals(0, username.getSortPosition());
        assertEquals(-1, surename.getSortPosition());
        assertEquals(-1, email.getSortPosition());
        surename.toggleOrder(); // ASCENDING
        assertEquals(0, username.getSortPosition());
        assertEquals(1, surename.getSortPosition());
        assertEquals(-1, email.getSortPosition());
        username.toggleOrder(); // DESCENDING
        email.toggleOrder(); // ASCENDING
        assertEquals(0, username.getSortPosition());
        assertEquals(1, surename.getSortPosition());
        assertEquals(2, email.getSortPosition());
        username.toggleOrder(); // NONE
        assertEquals(-1, username.getSortPosition());
        assertEquals(0, surename.getSortPosition());
        assertEquals(1, email.getSortPosition());
    }
}
