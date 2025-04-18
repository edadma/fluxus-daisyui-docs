package io.github.edadma.fluxus.daisyui.docs

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*

// Table page
val TablePage = () => {
  // Sample data for examples
  val users = List(
    Map("id" -> 1, "name" -> "John Doe", "email"      -> "john@example.com", "role"  -> "Admin"),
    Map("id" -> 2, "name" -> "Jane Smith", "email"    -> "jane@example.com", "role"  -> "User"),
    Map("id" -> 3, "name" -> "Alice Johnson", "email" -> "alice@example.com", "role" -> "Editor"),
    Map("id" -> 4, "name" -> "Bob Williams", "email"  -> "bob@example.com", "role"   -> "Admin"),
  )

  val simpleColumns = List(
    TableColumnDef(key = "id", title = "ID"),
    TableColumnDef(key = "name", title = "Name"),
    TableColumnDef(key = "email", title = "Email"),
  )

  val customColumns = List(
    TableColumnDef(
      key = "id",
      title = "ID",
      width = Some("60px"),
      align = "center",
      className = "font-mono",
    ),
    TableColumnDef(
      key = "name",
      title = "Name",
      minWidth = Some("150px"),
    ),
    TableColumnDef(
      key = "email",
      title = "Email",
      minWidth = Some("200px"),
      render = Some((value, _, _) =>
        a(
          href := s"mailto:${value}",
          cls  := "link link-primary",
          value.toString,
        ),
      ),
    ),
    TableColumnDef(
      key = "role",
      title = "Role",
      align = "center",
      render = Some((value, _, _) =>
        span(
          cls := {
            value match {
              case "Admin"  => "badge badge-primary"
              case "Editor" => "badge badge-secondary"
              case _        => "badge badge-ghost"
            }
          },
          value.toString,
        ),
      ),
    ),
  )

  val tableExamples = List(
    ExampleProps(
      title = "Basic Table",
      description = "A simple table with basic styling",
      example = Table <> TableProps(
        data = users,
        columns = simpleColumns,
        bordered = true,
      ),
      code = """import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def App: FluxusNode = {
  // Sample data
  val users = List(
    Map("id" -> 1, "name" -> "John Doe", "email" -> "john@example.com"),
    Map("id" -> 2, "name" -> "Jane Smith", "email" -> "jane@example.com"),
    Map("id" -> 3, "name" -> "Alice Johnson", "email" -> "alice@example.com")
  )

  // Column definitions
  val columns = List(
    TableColumnDef(key = "id", title = "ID"),
    TableColumnDef(key = "name", title = "Name"),
    TableColumnDef(key = "email", title = "Email")
  )

  // Table component
  Table <> TableProps(
    data = users,
    columns = columns,
    bordered = true
  )
}
""",
    ),
    ExampleProps(
      title = "Styled Table with Custom Renderers",
      description = "A table with custom styling, column alignment, and custom cell rendering",
      example = Table <> TableProps(
        data = users,
        columns = customColumns,
        bordered = true,
        striped = true,
        hover = true,
        headerBgClass = "bg-primary text-primary-content",
      ),
      code = """import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def App: FluxusNode = {
  // Sample data
  val users = List(
    Map("id" -> 1, "name" -> "John Doe", "email" -> "john@example.com", "role" -> "Admin"),
    Map("id" -> 2, "name" -> "Jane Smith", "email" -> "jane@example.com", "role" -> "User"),
    Map("id" -> 3, "name" -> "Alice Johnson", "email" -> "alice@example.com", "role" -> "Editor")
  )

  // Custom column definitions
  val columns = List(
    TableColumnDef(
      key = "id",
      title = "ID",
      width = Some("60px"),
      align = "center",
      className = "font-mono"
    ),
    TableColumnDef(
      key = "name",
      title = "Name",
      minWidth = Some("150px")
    ),
    TableColumnDef(
      key = "email",
      title = "Email",
      minWidth = Some("200px"),
      render = Some((value, _, _) =>
        a(
          href := s"mailto:${value}",
          cls := "link link-primary",
          value.toString
        )
      )
    ),
    TableColumnDef(
      key = "role",
      title = "Role",
      align = "center",
      render = Some((value, _, _) =>
        span(
          cls := {
            value match {
              case "Admin" => "badge badge-primary"
              case "Editor" => "badge badge-secondary"
              case _ => "badge badge-ghost"
            }
          },
          value.toString
        )
      )
    )
  )

  // Styled table component
  Table <> TableProps(
    data = users,
    columns = columns,
    bordered = true,
    striped = true,
    hover = true,
    headerBgClass = "bg-primary text-primary-content"
  )
}
""",
    ),
    ExampleProps(
      title = "Table with Pagination",
      description = "Table with built-in pagination",
      example = {
        val (currentPage, setCurrentPage, _) = useState(1)

        TableWithPagination <> TableWithPaginationProps(
          data = users,
          columns = customColumns,
          pageSize = 2,
          currentPage = currentPage,
          onPageChange = setCurrentPage,
          bordered = true,
          hover = true,
        )
      },
      code = """import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def App: FluxusNode = {
  // State for pagination
  val (currentPage, setCurrentPage, _) = useState(1)

  // Sample data and columns (as defined in previous examples)
  val users = // ... sample data
  val columns = // ... column definitions

  // Table with pagination
  TableWithPagination <> TableWithPaginationProps(
    data = users,
    columns = columns,
    pageSize = 2,
    currentPage = currentPage,
    onPageChange = setCurrentPage,
    bordered = true,
    hover = true
  )
}
""",
    ),
  )

  div(
    h1(cls := "text-3xl font-bold mb-2", "Table"),
    p(cls  := "mb-6", "A powerful table component for displaying data with sorting, pagination, and custom rendering."),

    // Examples
    h2(cls := "text-2xl font-bold mb-4", "Examples"),
    tableExamples.map(example => Example <> example),

    // TableColumnDef props
    h2(cls := "text-2xl font-bold mb-4", "TableColumnDef Props"),
    div(
      cls := "overflow-x-auto mb-8",
      table(
        cls := "table table-zebra w-full",
        thead(
          tr(
            th("Prop"),
            th("Type"),
            th("Default"),
            th("Description"),
          ),
        ),
        tbody(
          tr(
            td(code("key")),
            td(code("String")),
            td(i("required")),
            td("Data field key from row data"),
          ),
          tr(
            td(code("title")),
            td(code("String")),
            td(i("required")),
            td("Column header text"),
          ),
          tr(
            td(code("width")),
            td(code("Option[String]")),
            td(code("None")),
            td("Fixed column width (CSS value)"),
          ),
          tr(
            td(code("align")),
            td(code("String")),
            td(code("\"left\"")),
            td("Text alignment: left, center, right"),
          ),
          tr(
            td(code("render")),
            td(code("Option[(Any, Map[String, Any], Int) => FluxusNode]")),
            td(code("None")),
            td("Custom cell renderer (value, row, index)"),
          ),
        ),
      ),
    ),

    // Table props
    h2(cls := "text-2xl font-bold mb-4", "Table Props"),
    div(
      cls := "overflow-x-auto",
      table(
        cls := "table table-zebra w-full",
        thead(
          tr(
            th("Prop"),
            th("Type"),
            th("Default"),
            th("Description"),
          ),
        ),
        tbody(
          tr(
            td(code("data")),
            td(code("List[Map[String, Any]]")),
            td(code("List()")),
            td("Table data as list of maps"),
          ),
          tr(
            td(code("columns")),
            td(code("List[TableColumnDef]")),
            td(code("List()")),
            td("Column definitions"),
          ),
          tr(
            td(code("bordered")),
            td(code("Boolean")),
            td(code("true")),
            td("Whether to show borders"),
          ),
          tr(
            td(code("striped")),
            td(code("Boolean")),
            td(code("false")),
            td("Whether to use zebra striping"),
          ),
          tr(
            td(code("hover")),
            td(code("Boolean")),
            td(code("true")),
            td("Whether to highlight rows on hover"),
          ),
        ),
      ),
    ),
  )
}
