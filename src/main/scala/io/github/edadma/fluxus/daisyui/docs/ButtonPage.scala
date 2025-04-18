package io.github.edadma.fluxus.daisyui.docs

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*

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
      ),
      code = """import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def App: FluxusNode = {
  div(
    cls := "flex flex-wrap gap-2",
    Button <> ButtonProps(text = "Default"),
    Button <> ButtonProps(text = "Primary", variant = "primary"),
    Button <> ButtonProps(text = "Secondary", variant = "secondary"),
    Button <> ButtonProps(text = "Accent", variant = "accent")
  )
}
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
      code = """import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def App: FluxusNode = {
  div(
    cls := "flex flex-wrap gap-2 items-center",
    Button <> ButtonProps(text = "Large", size = "lg", variant = "primary"),
    Button <> ButtonProps(text = "Normal", variant = "primary"),
    Button <> ButtonProps(text = "Small", size = "sm", variant = "primary"),
    Button <> ButtonProps(text = "Tiny", size = "xs", variant = "primary")
  )
}
""",
    ),
    // Additional examples would be here
  )

  div(
    h1(cls := "text-3xl font-bold mb-2", "Button"),
    p(
      cls := "mb-6",
      "A versatile button component with comprehensive styling options, including all DaisyUI button variants.",
    ),

    // Examples
    h2(cls := "text-2xl font-bold mb-4", "Examples"),
    buttonExamples.map(example => Example <> example),

    // Props documentation
    h2(cls := "text-2xl font-bold mb-4", "Props"),
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
            td(code("text")),
            td(code("String")),
            td(code("\"\"")),
            td("Button text content"),
          ),
          tr(
            td(code("variant")),
            td(code("String")),
            td(code("\"primary\"")),
            td("Button variant: primary, secondary, accent, etc."),
          ),
          // Additional props would be listed here
        ),
      ),
    ),
  )
}
