package bootstrap

import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.events.Event
import react.RProps

abstract external class React {
    interface ElementType
    interface Any : ElementType
    interface String : ElementType
    interface HTMLAttributes<T : HTMLElement>
    interface ComponentPropsWithRef<T> : RProps
    interface SyntheticEvent
    interface HTMLProps<T : HTMLElement>
    interface ForwardRefExoticComponent<T>
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
    abstract class ClickEvent<T : HTMLElement> : Event { override val target: T? }
    abstract class ChangeEvent<T : HTMLElement> : Event { override val target: T? }
    abstract class SubmitEvent : Event { override val currentTarget: HTMLFormElement? }
}
