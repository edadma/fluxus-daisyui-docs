package io.github.edadma.fluxus.daisyui.docs

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*

// Components overview page with category headings and card layout
val ComponentsOverviewPage = () => {
  // Component info for cards
  val components = List(
    ComponentInfo(
      name = "Button",
      description = "Flexible buttons with extensive styling options.",
      href = "#button",
      example = div(
        cls := "flex flex-wrap gap-2",
        Button <> ButtonProps(text = "Primary", variant = "primary"),
        Button <> ButtonProps(text = "Secondary", variant = "secondary"),
        Button <> ButtonProps(text = "Accent", variant = "accent"),
      ),
      category = "Input Components",
    ),
    ComponentInfo(
      name = "Table",
      description = "Powerful tables for data display.",
      href = "#table",
      example = Table <> TableProps(
        data = List(
          Map("id" -> 1, "name" -> "John"),
          Map("id" -> 2, "name" -> "Jane"),
        ),
        columns = List(
          TableColumnDef(key = "id", title = "ID"),
          TableColumnDef(key = "name", title = "Name"),
        ),
        size = "xs",
        bordered = true,
      ),
      category = "Data Display Components",
    ),
    ComponentInfo(
      name = "Card",
      description = "Versatile content containers.",
      href = "#card",
      example = Card <> CardProps(
        title = Some("Sample Card"),
        children = div(
          p("This is an example of a card component."),
        ),
        bgClass = "bg-base-200",
      ),
      category = "Data Display Components",
    ),
    ComponentInfo(
      name = "Avatar",
      description = "User or item avatars with various shapes and sizes.",
      href = "#avatar",
      example = div(
        cls := "flex gap-2",
        Avatar <> AvatarProps(
          text = Some("JD"),
          bgClass = "bg-primary",
          textColorClass = "text-primary-content",
        ),
        Avatar <> AvatarProps(
          text = Some("AB"),
          bgClass = "bg-secondary",
          textColorClass = "text-secondary-content",
        ),
      ),
      category = "Data Display Components",
    ),
    ComponentInfo(
      name = "Badge",
      description = "Small status indicators or tags.",
      href = "#badge",
      example = div(
        cls := "flex gap-2",
        Badge <> BadgeProps(text = "New", variant = "primary"),
        Badge <> BadgeProps(text = "Success", variant = "success"),
        Badge <> BadgeProps(text = "Warning", variant = "warning"),
      ),
      category = "Data Display Components",
    ),
    ComponentInfo(
      name = "Alert",
      description = "Notification and feedback messages.",
      href = "#alert",
      example = Alert <> AlertProps(
        variant = "info",
        title = Some("Information"),
        message = Some("This is an informational alert."),
      ),
      category = "Feedback Components",
    ),
    ComponentInfo(
      name = "Input",
      description = "Form input elements with styling options.",
      href = "#input",
      example = Input <> InputProps(
        placeholder = Some("Enter text here..."),
        bordered = true,
      ),
      category = "Input Components",
    ),
    ComponentInfo(
      name = "Progress",
      description = "Progress indicators and loading state.",
      href = "#progress",
      example = div(
        Progress <> ProgressProps(
          value = Some(70.0),
          variant = "primary",
        ),
      ),
      category = "Feedback Components",
    ),
    // More components can be added here
  )

  // Group components by category
  val componentsByCategory = components.groupBy(_.category)
  val sortedCategories = List(
    "Layout Components",
    "Input Components",
    "Data Display Components",
    "Navigation Components",
    "Feedback Components",
  )

  div(
    h1(cls := "text-3xl font-bold mb-6", "Components Overview"),
    p(
      cls := "text-xl mb-8",
      "Browse our collection of UI components built with fluxus-daisyui.",
    ),

    // Render each category with its components
    sortedCategories.filter(category => componentsByCategory.contains(category)).map(category => {
      val categoryComponents = componentsByCategory(category)
      div(
        cls := "mb-12",
        // Category heading with component count
        div(
          cls := "flex items-center mb-6",
          h2(cls := "text-2xl font-bold", category),
          span(
            cls := "ml-2 px-2 py-1 text-sm rounded-md bg-base-200 text-base-content",
            categoryComponents.length.toString,
          ),
        ),
        // Component cards in a grid for this category
        div(
          cls := "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6",
          categoryComponents.map(comp => ComponentCard <> comp),
        ),
      )
    }),
  )
}

// Extended component info model with category
case class ComponentInfo(
    name: String,
    description: String,
    href: String,
    example: FluxusNode,
    category: String,
)

// Component card in overview - styled like the featured components section
val ComponentCard = (props: ComponentInfo) => {
  Card <> CardProps(
    children = div(
      h3(cls := "text-xl font-bold mb-2", props.name),
      p(cls  := "mb-4", props.description),
      div(
        cls := "mb-4",
        props.example,
      ),
      div(
        cls := "mt-4",
        a(
          href := props.href,
          cls  := "btn btn-sm btn-ghost",
          "View Documentation",
        ),
      ),
    ),
  )
}
