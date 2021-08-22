public class _4_UsingServices {

    /* A service is composed of an interface, any classes the interface references, and a way of looking up
       implementations of the interface.

       NOTE: The implementations are not part of the service */

    /* Declaring the Service Provider Interface
         - This module contains a Java interface type. This interface is called the service provider interface
           because it specifies what behavior our service will have

           Such a Module usually:
             - Exports      itself
     */

    /* Creating a Service Locator
         - To complete our service, we need a service locator. A service locator is able to find any classes that
           implement a service provider interface.
         - Luckily, Java provides a ServiceLocator class to help with this task. You pass the service provider
           interface type to its load() method, and Java will return any implementation services it can find.

           Such a Module usually:
             - Exports      itself
             - Requires     the ServiceProviderInterfaceModule
             - Uses         the interface in the ServiceProviderInterfaceModule
     */

    /* NOW THAT WE HAVE THE INTERFACE AND LOOKUP LOGIC, WE HAVE COMPLETED OUR SERVICE. */

    /* Invoking from a Consumer
         - Next up is to call the service locator by a consumer. A consumer (or client) refers to a module that
           obtains and uses a service.
         - Once the consumer has acquired a service via the service locator, it is able to invoke the methods
           provided by the service provider interface.

           Such a Module usually:
             - Requires     the ServiceProviderInterfaceModule
             - Requires     the ServiceLocatorModule
     */

    /* Adding a Service Provider
         - A service provider is the implementation of a service provider interface. As we said earlier, at
           runtime it is possible to have multiple implementation classes or modules

           Such a Module usually:
             - Requires     the ServiceProviderInterfaceModule
             - Provides     the interface in the ServiceProviderInterfaceModule
               With         the class in the ServiceProviderModule
     */

    /* Merging Service Locator and Consumer
         NOTE: Conveniently, if you call ServiceLoader.load(), it returns an object that you can loop through
               normally. However, requesting a Stream gives you a different type. The reason for this is that a
               Stream controls when elements are evaluated. Therefore, a ServiceLoader returns a Stream of Provider
               objects. You have to call get() to retrieve the value you wanted out of each Provider.

        - This Module serves as a ServiceProviderModule and a ConsumerModule

        Such a Module usually:
             - Requires     the ServiceProviderInterfaceModule
             - Provides     the interface in the ServiceProviderInterfaceModule
               With         the class in the ServiceProviderModule
             - Uses         the interface in the ServiceProviderInterfaceModule
     */

}
