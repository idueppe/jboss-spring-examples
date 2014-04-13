package io.crowdcode.vehicle.security.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class UserFilterParameters implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private EnumMap<UserColumn, ColumnEntry> columns = new EnumMap<>(UserColumn.class);
    Map<String, ColumnEntry> stringColumns = new HashMap<>();
    
    private List<UserColumn> sortOrder = new ArrayList<>();
    
    private JunctionType junction = JunctionType.CONJUNCTION;
    
    public UserFilterParameters() {
        for (UserColumn column : EnumSet.allOf(UserColumn.class)) {
            ColumnEntry entry = new ColumnEntry(column);
            columns.put(column, entry);
            stringColumns.put(column.toString(), entry);
        }
    }

    public Map<String, ColumnEntry> getColumns() {
        return Collections.unmodifiableMap(stringColumns);
    }
    
    public ColumnEntry getColumn(UserColumn column) {
        return columns.get(column);
    }

    public boolean isDisjunction() {
        return junction == JunctionType.DISJUNCTION;
    }

    public boolean isConjunction() {
        return junction == JunctionType.CONJUNCTION;
    }
    
    public void toggleJunction() {
        if (junction == JunctionType.DISJUNCTION) {
            junction = JunctionType.CONJUNCTION;
        } else {
            junction = JunctionType.DISJUNCTION;
        }
    }
    
    public List<ColumnEntry> getSortedColumns() {
        List<ColumnEntry> entries = new LinkedList<>();
        for (UserColumn column : sortOrder) {
            entries.add(columns.get(column));
        }
        return entries;
    }
    
    public class ColumnEntry implements Serializable {
        
        private static final long serialVersionUID = 1L;

        private SortOrder order = SortOrder.NONE;
        private String filter;
        private UserColumn column;
        
        private ColumnEntry(UserColumn column) {
            this.column = column;
        }
        
        public void toggleOrder() {
            if (order == SortOrder.ASCENDING) {
                order = SortOrder.DESCENDING;
            } else if (order == SortOrder.DESCENDING) {
                sortOrder.remove(column);
                order = SortOrder.NONE;
            } else {
                sortOrder.add(column);
                order = SortOrder.ASCENDING;
            }
            System.out.println("toggling sort order of "+column + " to "+order);
        }

        public SortOrder getSortOrder() {
            return order;
        }
        
        public int getSortPosition() {
            return sortOrder.indexOf(column);
        }

        public String getFilter() {
            return filter;
        }
        
        public String wildcharFilter() {
            if (filter == null || filter.trim().equals("")) {
                return null;
            } else {
                return "%"+filter+"%";
            }
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }

        public UserColumn getColumn() {
            return column;
        }
    }

}
