package bootstrap

import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.events.Event
import react.Component
import react.RProps
import react.RState

abstract external class React {
    interface ElementType
    interface Any : ElementType
    interface String : ElementType
    interface HTMLAttributes<T : HTMLElement>
    interface ComponentPropsWithRef<T> : RProps
    interface SyntheticEvent
    interface HTMLProps<T : HTMLElement> : RProps
    abstract class ForwardRefExoticComponent<T : RProps>(props: T) : Component<T, RState>
    interface ClassAttributes<T>
    interface ReactElement
    interface OlHTMLAttributes<T>
    interface ReactNode
    interface MouseEventHandler<T>
    interface LegacyRef<T>
    interface FormEventHandler<in T>
    interface KeyboardEventHandler<T>
    interface ReactChild
    interface ChangeEventHandler<T>
    abstract class ClickEvent<out T : HTMLElement> : Event { override val target: T? }
    abstract class ChangeEvent<T : HTMLElement> : Event { override val target: T? }
    abstract class SubmitEvent : Event { override val currentTarget: HTMLFormElement? }
}
