package io.github.edadma.fluxus.daisyui.docs

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*
import org.scalajs.dom

// Main application entry point
def App: FluxusNode = {
  // Simple hash-based routing
  val (route, setRoute, _) = useState(
    Option(dom.window.location.hash)
      .filter(_.nonEmpty)
      .map(_.substring(1))
      .getOrElse("home"),
  )

  // Listen for hash changes
  useEffect(
    () => {
      val handleHashChange = (_: dom.Event) => {
        val newRoute = Option(dom.window.location.hash)
          .filter(_.nonEmpty)
          .map(_.substring(1))
          .getOrElse("home")

        setRoute(newRoute)
      }

      dom.window.addEventListener("hashchange", handleHashChange)

      () => dom.window.removeEventListener("hashchange", handleHashChange)
    },
    Seq(),
  )

  // Routing logic
  val content = route match {
    case "home"   => HomePage <> ()
    case "button" => ButtonPage <> ()
    case "table"  => TablePage <> ()
    case _        => NotFoundPage <> ()
  }

  div(
    cls          := "drawer lg:drawer-open",
    "data_theme" := "light",
    input(
      typ := "checkbox",
      id  := "drawer-toggle",
      cls := "drawer-toggle",
    ),
    div(
      cls := "drawer-content flex flex-col",
      // Header
      div(
        cls := "sticky top-0 z-30 flex h-16 w-full justify-between bg-base-100 bg-opacity-90 shadow-sm lg:hidden",
        div(
          cls := "navbar w-full",
          div(
            cls := "flex-none",
            label(
              htmlFor := "drawer-toggle",
              cls     := "btn btn-square btn-ghost drawer-button",
              svg(
                xmlns   := "http://www.w3.org/2000/svg",
                fill    := "none",
                viewBox := "0 0 24 24",
                cls     := "inline-block w-5 h-5 stroke-current",
                path(
                  strokeLinecap  := "round",
                  strokeLinejoin := "round",
                  strokeWidth    := "2",
                  d              := "M4 6h16M4 12h16M4 18h16",
                ),
              ),
            ),
          ),
          div(
            cls := "flex-1",
            a(
              href := "#home",
              cls  := "text-xl font-bold",
              "Fluxus DaisyUI",
            ),
          ),
          div(
            cls := "flex-none",
            a(
              href   := "https://github.com/edadma/fluxus-daisyui",
              cls    := "btn btn-ghost btn-square",
              target := "_blank",
              svg(
                xmlns   := "http://www.w3.org/2000/svg",
                width   := "24",
                height  := "24",
                viewBox := "0 0 24 24",
                cls     := "fill-current",
                path(
                  d := "M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z",
                ),
              ),
            ),
          ),
        ),
      ),

      // Main content
      div(
        cls := "px-6 py-8 main-content",
        content,
      ),
    ),

    // Sidebar
    div(
      cls := "drawer-side z-40",
      label(
        htmlFor    := "drawer-toggle",
        aria_label := "close sidebar",
        cls        := "drawer-overlay",
      ),
      aside(
        cls := "bg-base-200 w-72 min-h-full",
        div(
          cls := "sticky top-0 z-20 items-center gap-2 bg-base-200 bg-opacity-90 p-4 hidden lg:flex",
          a(
            href := "#home",
            cls  := "text-2xl font-bold",
            "Fluxus DaisyUI",
          ),
        ),
        div(
          cls := "h-4",
        ),
        ul(
          cls := "menu menu-sm lg:menu-md px-4 py-0",
          li(
            a(
              href := "#home",
              cls  := s"py-3 ${if (route == "home") "active" else ""}",
              "Home",
            ),
          ),
          li(
            span(
              cls := "py-3 text-base-content/50",
              "Components",
            ),
          ),
          li(
            a(
              href := "#button",
              cls  := s"py-3 ${if (route == "button") "active" else ""}",
              "Button",
            ),
          ),
          li(
            a(
              href := "#table",
              cls  := s"py-3 ${if (route == "table") "active" else ""}",
              "Table",
            ),
          ),
        ),
      ),
    ),
  )
}

// Component example container
case class ExampleProps(
    title: String,
    description: String,
    example: FluxusNode,
    code: String,
)

val Example = (props: ExampleProps) => {
  val (showCode, setShowCode, _) = useState(false)

  Card <> CardProps(
    className = "mb-8",
    children = div(
      div(
        cls := "mb-4",
        h3(cls := "text-xl font-bold", props.title),
        p(props.description),
      ),
      div(
        cls := "p-6 border rounded-md bg-base-100",
        props.example,
      ),
      div(
        cls := "mt-4 flex justify-end",
        Button <> ButtonProps(
          text = if (showCode) "Hide Code" else "Show Code",
          variant = "ghost",
          size = "sm",
          onClick = () => setShowCode(!showCode),
        ),
      ),
      if (showCode) {
        div(
          cls := "mt-4 p-4 bg-base-300 rounded-md overflow-auto",
          pre(code(props.code)),
        )
      } else null,
    ),
  )
}

// Home page
val HomePage = () => {
  div(
    h1(cls := "text-4xl font-bold mb-6", "Fluxus DaisyUI"),
    p(
      cls := "text-xl mb-6",
      "A comprehensive component library for Fluxus implementing the DaisyUI component system with full Tailwind CSS integration.",
    ),
    div(
      cls := "grid grid-cols-1 md:grid-cols-2 gap-6 mt-8",
      Card <> CardProps(
        title = Some("Getting Started"),
        children = div(
          p("Add the dependency to your build.sbt:"),
          pre(
            cls := "bg-base-300 p-4 rounded mt-2 overflow-auto",
            code("""libraryDependencies += "io.github.edadma" %%% "fluxus-daisyui" % "0.0.3""""),
          ),
          div(
            cls := "mt-4",
            Button <> ButtonProps(
              text = "Installation Guide",
              variant = "primary",
            ),
          ),
        ),
      ),
      Card <> CardProps(
        title = Some("Features"),
        children = div(
          ul(
            cls := "list-disc list-inside space-y-2",
            li("Ready-to-use DaisyUI components"),
            li("Type-safe API with Scala's type system"),
            li("Comprehensive styling options"),
            li("Responsive design support"),
            li("Accessibility features built-in"),
            li("Seamless integration with Fluxus"),
          ),
        ),
      ),
    ),
    h2(cls := "text-2xl font-bold mt-12 mb-4", "Featured Components"),
    div(
      cls := "grid grid-cols-1 md:grid-cols-2 gap-6",

      // Button card
      Card <> CardProps(
        children = div(
          h3(cls := "text-xl font-bold mb-2", "Button"),
          p(cls  := "mb-4", "Flexible buttons with extensive styling options."),
          div(
            cls := "flex flex-wrap gap-2",
            Button <> ButtonProps(text = "Primary", variant = "primary"),
            Button <> ButtonProps(text = "Secondary", variant = "secondary"),
            Button <> ButtonProps(text = "Accent", variant = "accent"),
          ),
          div(
            cls := "mt-4",
            a(
              href := "#button",
              cls  := "btn btn-sm btn-ghost",
              "View Documentation",
            ),
          ),
        ),
      ),

      // Table card
      Card <> CardProps(
        children = div(
          h3(cls := "text-xl font-bold mb-2", "Table"),
          p(cls  := "mb-4", "Powerful tables for data display."),
          div(
            cls := "border rounded",
            table(
              cls := "table table-xs",
              thead(
                tr(
                  th("ID"),
                  th("Name"),
                ),
              ),
              tbody(
                tr(
                  td("1"),
                  td("John"),
                ),
                tr(
                  td("2"),
                  td("Jane"),
                ),
              ),
            ),
          ),
          div(
            cls := "mt-4",
            a(
              href := "#table",
              cls  := "btn btn-sm btn-ghost",
              "View Documentation",
            ),
          ),
        ),
      ),
    ),
  )
}

// Button page
val ButtonPage = () => {
  val buttonExamples = List(
    ExampleProps(
      title = "Basic Buttons",
      description = "Basic button examples with different variants",
      example = div(
        cls := "flex flex-wrap gap-2",
        Button <> ButtonProps(text = "Default"),
        Button <> ButtonProps(text = "Primary", variant = "primary"),
        Button <> ButtonProps(text = "Secondary", variant = "secondary"),
        Button <> ButtonProps(text = "Accent", variant = "accent"),
        Button <> ButtonProps(text = "Info", variant = "info"),
        Button <> ButtonProps(text = "Success", variant = "success"),
        Button <> ButtonProps(text = "Warning", variant = "warning"),
        Button <> ButtonProps(text = "Error", variant = "error"),
      ),
      code = """
Button <> ButtonProps(text = "Default")
Button <> ButtonProps(text = "Primary", variant = "primary")
Button <> ButtonProps(text = "Secondary", variant = "secondary")
Button <> ButtonProps(text = "Accent", variant = "accent")
Button <> ButtonProps(text = "Info", variant = "info")
Button <> ButtonProps(text = "Success", variant = "success")
Button <> ButtonProps(text = "Warning", variant = "warning")
Button <> ButtonProps(text = "Error", variant = "error")
""",
    ),
    ExampleProps(
      title = "Button Sizes",
      description = "Buttons in different sizes",
      example = div(
        cls := "flex flex-wrap gap-2 items-center",
        Button <> ButtonProps(text = "Large", size = "lg", variant = "primary"),
        Button <> ButtonProps(text = "Normal", variant = "primary"),
        Button <> ButtonProps(text = "Small", size = "sm", variant = "primary"),
        Button <> ButtonProps(text = "Tiny", size = "xs", variant = "primary"),
      ),
      code = """
Button <> ButtonProps(text = "Large", size = "lg", variant = "primary")
Button <> ButtonProps(text = "Normal", variant = "primary")
Button <> ButtonProps(text = "Small", size = "sm", variant = "primary")
Button <> ButtonProps(text = "Tiny", size = "xs", variant = "primary")
""",
    ),
    ExampleProps(
      title = "Button Styles",
      description = "Various button styles including outline, soft, and dash variants",
      example = div(
        cls := "space-y-4",
        div(
          cls := "flex flex-wrap gap-2",
          Button <> ButtonProps(text = "Primary", variant = "primary"),
          Button <> ButtonProps(text = "Outline", variant = "primary", outline = true),
          Button <> ButtonProps(text = "Soft", variant = "primary", soft = true),
          Button <> ButtonProps(text = "Dash", variant = "primary", dash = true),
        ),
        div(
          cls := "flex flex-wrap gap-2",
          Button <> ButtonProps(text = "Wide", variant = "secondary", wide = true),
          Button <> ButtonProps(text = "Block", variant = "secondary", block = true),
        ),
      ),
      code = """
// Different styles
Button <> ButtonProps(text = "Primary", variant = "primary")
Button <> ButtonProps(text = "Outline", variant = "primary", outline = true)
Button <> ButtonProps(text = "Soft", variant = "primary", soft = true)
Button <> ButtonProps(text = "Dash", variant = "primary", dash = true)

// Width options
Button <> ButtonProps(text = "Wide", variant = "secondary", wide = true)
Button <> ButtonProps(text = "Block", variant = "secondary", block = true)
""",
    ),
    ExampleProps(
      title = "Button States",
      description = "Buttons in different states",
      example = div(
        cls := "flex flex-wrap gap-2",
        Button <> ButtonProps(text = "Normal", variant = "primary"),
        Button <> ButtonProps(text = "Active", variant = "primary", active = true),
        Button <> ButtonProps(text = "Disabled", variant = "primary", disabled = true),
        Button <> ButtonProps(text = "Loading", variant = "primary", loading = true),
      ),
      code = """
Button <> ButtonProps(text = "Normal", variant = "primary")
Button <> ButtonProps(text = "Active", variant = "primary", active = true)
Button <> ButtonProps(text = "Disabled", variant = "primary", disabled = true)
Button <> ButtonProps(text = "Loading", variant = "primary", loading = true)
""",
    ),
    ExampleProps(
      title = "Buttons with Icons",
      description = "Buttons with start and end icons",
      example = div(
        cls := "flex flex-wrap gap-2",
        Button <> ButtonProps(
          text = "Download",
          variant = "primary",
          startIcon = Some(
            svg(
              xmlns   := "http://www.w3.org/2000/svg",
              cls     := "h-5 w-5",
              fill    := "none",
              viewBox := "0 0 24 24",
              stroke  := "currentColor",
              path(
                strokeLinecap  := "round",
                strokeLinejoin := "round",
                strokeWidth    := "2",
                d              := "M4 16v1a3 3 0 003 3h10a3 3 0 003-3v-1m-4-4l-4 4m0 0l-4-4m4 4V4",
              ),
            ),
          ),
        ),
        Button <> ButtonProps(
          text = "Next",
          variant = "secondary",
          endIcon = Some(
            svg(
              xmlns   := "http://www.w3.org/2000/svg",
              cls     := "h-5 w-5",
              fill    := "none",
              viewBox := "0 0 24 24",
              stroke  := "currentColor",
              path(
                strokeLinecap  := "round",
                strokeLinejoin := "round",
                strokeWidth    := "2",
                d              := "M9 5l7 7-7 7",
              ),
            ),
          ),
        ),
        Button <> ButtonProps(
          variant = "accent",
          shape = Some("circle"),
          children = Some(
            svg(
              xmlns   := "http://www.w3.org/2000/svg",
              cls     := "h-6 w-6",
              fill    := "none",
              viewBox := "0 0 24 24",
              stroke  := "currentColor",
              path(
                strokeLinecap  := "round",
                strokeLinejoin := "round",
                strokeWidth    := "2",
                d              := "M12 4v16m8-8H4",
              ),
            ),
          ),
        ),
      ),
      code = """
Button <> ButtonProps(
  text = "Download",
  variant = "primary",
  startIcon = Some(
    svg(
      xmlns := "http://www.w3.org/2000/svg",
      cls := "h-5 w-5",
      fill := "none",
      viewBox := "0 0 24 24",
      stroke := "currentColor",
      path(
        strokeLinecap := "round",
        strokeLinejoin := "round",
        strokeWidth := "2",
        d := "M4 16v1a3 3 0 003 3h10a3 3 0 003-3v-1m-4-4l-4 4m0 0l-4-4m4 4V4"
      )
    )
  )
)

Button <> ButtonProps(
  text = "Next",
  variant = "secondary",
  endIcon = Some(
    svg(
      xmlns := "http://www.w3.org/2000/svg",
      cls := "h-5 w-5",
      fill := "none",
      viewBox := "0 0 24 24",
      stroke := "currentColor",
      path(
        strokeLinecap := "round",
        strokeLinejoin := "round",
        strokeWidth := "2",
        d := "M9 5l7 7-7 7"
      )
    )
  )
)

Button <> ButtonProps(
  variant = "accent",
  shape = Some("circle"),
  children = Some(
    svg(
      xmlns := "http://www.w3.org/2000/svg",
      cls := "h-6 w-6",
      fill := "none",
      viewBox := "0 0 24 24",
      stroke := "currentColor",
      path(
        strokeLinecap := "round",
        strokeLinejoin := "round",
        strokeWidth := "2",
        d := "M12 4v16m8-8H4"
      )
    )
  )
)
""",
    ),
  )

  div(
    h1(cls := "text-3xl font-bold mb-2", "Button"),
    p(
      cls := "mb-6",
      "A versatile button component with comprehensive styling options, including all DaisyUI button variants.",
    ),

    // Import section
    div(
      cls := "mb-8",
      h2(cls := "text-2xl font-bold mb-4", "Import"),
      div(
        cls := "bg-base-300 p-4 rounded-md overflow-auto",
        pre(code("""import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._""")),
      ),
    ),

    // Examples
    h2(cls := "text-2xl font-bold mb-4", "Examples"),
    buttonExamples.map(example => Example <> example),

    // Props documentation
    h2(cls := "text-2xl font-bold mb-4", "Props"),
    div(
      cls := "overflow-x-auto",
      table(
        cls := "table table-zebra table-sm md:table-md",
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
            td(code("text")),
            td(code("String")),
            td(code("\"\"")),
            td("Button text content"),
          ),
          tr(
            td(code("children")),
            td(code("Option[FluxusNode]")),
            td(code("None")),
            td("Alternative to text for complex content"),
          ),
          tr(
            td(code("variant")),
            td(code("String")),
            td(code("\"primary\"")),
            td("Button variant: primary, secondary, accent, info, success, warning, error, ghost, link, neutral"),
          ),
          tr(
            td(code("size")),
            td(code("String")),
            td(code("\"md\"")),
            td("Button size: lg, md, sm, xs"),
          ),
          tr(
            td(code("shape")),
            td(code("Option[String]")),
            td(code("None")),
            td("Button shape: circle, square"),
          ),
          tr(
            td(code("soft")),
            td(code("Boolean")),
            td(code("false")),
            td("Whether to use soft style (lower-opacity background)"),
          ),
          tr(
            td(code("dash")),
            td(code("Boolean")),
            td(code("false")),
            td("Whether to use dashed border style"),
          ),
          tr(
            td(code("outline")),
            td(code("Boolean")),
            td(code("false")),
            td("Whether to use outline style"),
          ),
          tr(
            td(code("wide")),
            td(code("Boolean")),
            td(code("false")),
            td("Whether to use wide style"),
          ),
          tr(
            td(code("block")),
            td(code("Boolean")),
            td(code("false")),
            td("Whether to make button full width"),
          ),
          tr(
            td(code("loading")),
            td(code("Boolean")),
            td(code("false")),
            td("Whether to show loading state"),
          ),
          tr(
            td(code("disabled")),
            td(code("Boolean")),
            td(code("false")),
            td("Whether button is disabled"),
          ),
          tr(
            td(code("onClick")),
            td(code("() => Unit")),
            td(code("() => ()")),
            td("Click handler function"),
          ),
          tr(
            td(code("startIcon")),
            td(code("Option[FluxusNode]")),
            td(code("None")),
            td("Icon to display at start of button"),
          ),
          tr(
            td(code("endIcon")),
            td(code("Option[FluxusNode]")),
            td(code("None")),
            td("Icon to display at end of button"),
          ),
          tr(
            td(code("className")),
            td(code("String")),
            td(code("\"\"")),
            td("Additional CSS classes"),
          ),
        ),
      ),
    ),
  )
}

// Table page
val TablePage = () => {
  // Sample data for examples
  val users = List(
    Map("id" -> 1, "name" -> "John Doe", "email"      -> "john@example.com", "role"    -> "Admin"),
    Map("id" -> 2, "name" -> "Jane Smith", "email"    -> "jane@example.com", "role"    -> "User"),
    Map("id" -> 3, "name" -> "Alice Johnson", "email" -> "alice@example.com", "role"   -> "Editor"),
    Map("id" -> 4, "name" -> "Bob Williams", "email"  -> "bob@example.com", "role"     -> "Admin"),
    Map("id" -> 5, "name" -> "Charlie Brown", "email" -> "charlie@example.com", "role" -> "User"),
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
      code = """
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
      code = """
val customColumns = List(
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

Table <> TableProps(
  data = users,
  columns = customColumns,
  bordered = true,
  striped = true,
  hover = true,
  headerBgClass = "bg-primary text-primary-content"
)
""",
    ),
    ExampleProps(
      title = "Table Sizes and Empty State",
      description = "Tables with different sizes and empty state handling",
      example = div(
        cls := "space-y-8",
        div(
          h3(cls := "text-lg font-bold mb-2", "Compact Size"),
          Table <> TableProps(
            data = users.take(3),
            columns = simpleColumns,
            bordered = true,
            size = "sm",
            compact = true,
          ),
        ),
        div(
          h3(cls := "text-lg font-bold mb-2", "Empty Table"),
          Table <> TableProps(
            data = List(),
            columns = simpleColumns,
            bordered = true,
            emptyText = "No users found",
          ),
        ),
      ),
      code = """
// Compact table
Table <> TableProps(
  data = users.take(3),
  columns = simpleColumns,
  bordered = true,
  size = "sm",
  compact = true
)

// Empty table with custom message
Table <> TableProps(
  data = List(),
  columns = simpleColumns,
  bordered = true,
  emptyText = "No users found"
)
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
      code = """
val (currentPage, setCurrentPage, _) = useState(1)

TableWithPagination <> TableWithPaginationProps(
  data = users,
  columns = customColumns,
  pageSize = 2,
  currentPage = currentPage,
  onPageChange = setCurrentPage,
  bordered = true,
  hover = true
)
""",
    ),
  )

  div(
    h1(cls := "text-3xl font-bold mb-2", "Table"),
    p(cls  := "mb-6", "A powerful table component for displaying data with sorting, pagination, and custom rendering."),

    // Import section
    div(
      cls := "mb-8",
      h2(cls := "text-2xl font-bold mb-4", "Import"),
      div(
        cls := "bg-base-300 p-4 rounded-md overflow-auto",
        pre(code("""import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._""")),
      ),
    ),

    // Examples
    h2(cls := "text-2xl font-bold mb-4", "Examples"),
    tableExamples.map(example => Example <> example),

    // TableColumnDef props
    h2(cls := "text-2xl font-bold mb-4", "TableColumnDef Props"),
    div(
      cls := "overflow-x-auto mb-8",
      table(
        cls := "table table-zebra table-sm md:table-md",
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
            td(code("minWidth")),
            td(code("Option[String]")),
            td(code("None")),
            td("Minimum column width (CSS value)"),
          ),
          tr(
            td(code("maxWidth")),
            td(code("Option[String]")),
            td(code("None")),
            td("Maximum column width (CSS value)"),
          ),
          tr(
            td(code("align")),
            td(code("String")),
            td(code("\"left\"")),
            td("Text alignment: left, center, right"),
          ),
          tr(
            td(code("hidden")),
            td(code("Boolean")),
            td(code("false")),
            td("Whether to hide the column"),
          ),
          tr(
            td(code("render")),
            td(code("Option[(Any, Map[String, Any], Int) => FluxusNode]")),
            td(code("None")),
            td("Custom cell renderer (value, row, index)"),
          ),
          tr(
            td(code("className")),
            td(code("String")),
            td(code("\"\"")),
            td("Additional CSS classes for cells"),
          ),
          tr(
            td(code("headerClassName")),
            td(code("String")),
            td(code("\"\"")),
            td("Additional CSS classes for header cell"),
          ),
        ),
      ),
    ),

    // Table props
    h2(cls := "text-2xl font-bold mb-4", "Table Props"),
    div(
      cls := "overflow-x-auto",
      table(
        cls := "table table-zebra table-sm md:table-md",
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
            td(code("keyField")),
            td(code("String")),
            td(code("\"id\"")),
            td("Field to use as unique row key"),
          ),
          tr(
            td(code("emptyText")),
            td(code("String")),
            td(code("\"No data available\"")),
            td("Text shown when data is empty"),
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
          tr(
            td(code("compact")),
            td(code("Boolean")),
            td(code("false")),
            td("Whether to use compact styling"),
          ),
          tr(
            td(code("size")),
            td(code("String")),
            td(code("\"md\"")),
            td("Table size: sm, md, lg"),
          ),
          tr(
            td(code("responsive")),
            td(code("Boolean")),
            td(code("true")),
            td("Whether to enable horizontal scrolling"),
          ),
          tr(
            td(code("headerBgClass")),
            td(code("String")),
            td(code("\"\"")),
            td("Header background CSS class"),
          ),
          tr(
            td(code("onRowClick")),
            td(code("Option[(Map[String, Any], Int) => Unit]")),
            td(code("None")),
            td("Row click handler (row, index)"),
          ),
          tr(
            td(code("caption")),
            td(code("Option[String]")),
            td(code("None")),
            td("Table caption"),
          ),
          tr(
            td(code("footer")),
            td(code("Option[FluxusNode]")),
            td(code("None")),
            td("Custom footer content"),
          ),
        ),
      ),
    ),
  )
}

// Not found page
val NotFoundPage = () => {
  div(
    cls := "flex flex-col items-center justify-center h-[70vh]",
    h1(cls := "text-4xl font-bold mb-4", "404"),
    p(cls  := "text-xl mb-8", "Page not found"),
    a(
      href := "#home",
      cls  := "btn btn-primary",
      "Back to Home",
    ),
  )
}
