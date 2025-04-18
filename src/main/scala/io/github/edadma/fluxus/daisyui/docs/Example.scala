package io.github.edadma.fluxus.daisyui.docs

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*

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
