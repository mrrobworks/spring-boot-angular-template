import { ElementRef } from '@angular/core';
import {
  Column,
  ColumnApi,
  ColDef,
  GridApi,
  GridOptions
} from 'ag-grid-community';

export abstract class AgGridBase {
  api: GridApi;
  gridOptions: GridOptions;
  columnDefs: ColDef[];

  protected constructor(private divAgGrid: ElementRef) {
    this.divAgGrid = divAgGrid;
    this.gridOptions = AgGridBase.defaultGridOptions();
    this.columnDefs = this.createColumnDefs();
  }

  private static defaultGridOptions(): GridOptions {
    const gridOptions: GridOptions = <GridOptions>{
      rowHeight: 32,
      rowStyle: { background: '#d9e2f3' }
    };

    gridOptions.getRowStyle = function(params) {
      if (params.node.rowIndex % 2 === 0) {
        return { background: '#b4c6e7' };
      }
    };

    return gridOptions;
  }

  abstract createColumnDefs(): ColDef[];

  resizeColumns(columnApi: ColumnApi) {
    const gridWidth = this.divAgGrid.nativeElement.offsetWidth;
    let columnWidthWithoutLastColumn = 0;
    const allColumns = columnApi.getAllColumns();
    const columnsWithoutLast = [];
    let allColumnsSize = 0;

    columnApi.autoSizeColumns(allColumns);

    for (let i = 0; i < allColumns.length; i++) {
      const column = allColumns[i];
      allColumnsSize += column.getActualWidth();

      if (allColumns.length - 1 !== i) {
        columnWidthWithoutLastColumn += column.getActualWidth();
        columnsWithoutLast.push(column);
      }
    }

    const lastColumn = allColumns[allColumns.length - 1] as Column;

    if (gridWidth > allColumnsSize) {
      const offset = 0;
      lastColumn.setActualWidth(
        gridWidth - columnWidthWithoutLastColumn - offset
      );
      columnApi.autoSizeColumns(columnsWithoutLast);
    }
  }

  rowsSelected(): boolean {
    return this.api && this.api.getSelectedRows().length > 0;
  }
}
