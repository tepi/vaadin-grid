package com.vaadin.elements.grid.config;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

import com.google.gwt.dom.client.Element;
import com.vaadin.client.widget.grid.CellReference;
import com.vaadin.elements.common.js.JS;
import com.vaadin.elements.grid.data.GridDataSource;
import com.vaadin.elements.grid.table.GridColumn;

/**
 * This class is a JsInterop wrapper for the JS object representing a cell
 * object passed to cell style generator.
 */
@JsType(isNative=true)
public interface JSCell {

    @JsOverlay
    static JSCell create(CellReference<Object> cell, Element container) {
        JSCell jsCell = JS.createJsObject();
        GridColumn column = (GridColumn) cell.getColumn();

        jsCell.setElement(cell.getElement());
        jsCell.setIndex(cell.getColumnIndex());
        jsCell.setColumnName(column.getJsColumn().getName());
        jsCell.setData(column.getValue(cell.getRow()));

        JSRow row = JS.createJsObject();
        row.setIndex(cell.getRowIndex());
        row.setData(GridDataSource.extractDataItem(cell.getRow()));
        row.setElement(cell.getElement().getParentElement());

        row.setGrid(container);
        jsCell.setRow(row);
        return jsCell;
    }

    @JsProperty String getColumnName();

    @JsProperty void setColumnName(String columnName);

    @JsProperty Element getElement();

    @JsProperty void setElement(Element element);

    @JsProperty JSRow getRow();

    @JsProperty void setRow(JSRow row);

    @JsProperty Object getData();

    @JsProperty void setData(Object data);

    @JsProperty int getIndex();

    @JsProperty void setIndex(int index);

}
