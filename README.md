# hotel-room-booking-app

hotel-room-booking-app is used to book rooms for a single hotel.

Different **microservices** involved are –
1. **API-Gateway** - This service is responsible for routing all requests to the
microservices internally.
1. **Booking service** - This service is responsible for collecting all information
related to user booking and sending a confirmation message once the
booking is confirmed.
1. **Payment service** - This is a dummy payment service; this service is called by
the booking service for initiating payment after confirming rooms.
1. **Service registry** – This service is Eureka Server and is responsible for registry
of services.

**Port Numbers** –
* API-Gateway: *9191*
* Booking service: *8081*
* Payment service: *8083*
* Service registry: *8761*

**Database used** – in memory spring h2 database.

Link for [API Documentation](https://documenter.getpostman.com/view/17097842/UV5c8uab/)

<br />

## Booking Service 

2 separate **DTOs** are used to handle custom request and to give custom response
to clients –
* BookingInfoRequestDto
* BookingInfoResponseDto

**Model Mapper** is used to convert entity to DTO and vice versa. This conversion is
handled in BookingServiceImp class.

The **room numbers** displayed are not based on the availability of vacant rooms.
They are rather randomly generated integers between 1 and 100.

The logic to calculate **room price** is:
* `roomPrice = 1000 * numOfRooms * days;` Here, 1000 INR is the base
price/day/room, which is set in BookingInfoEntity’s roomPrice attribute.
* Days is the difference between fromDate and toDate. If both the dates are
same, then roomPrice is calculated for 1 day. If fromDate is greater than
toDate then an exception is thrown.

A **confirmation message** is printed on console upon acceptance of payment
details.

**Rest Template** is used for synchronous communication between Booking and
Payment service.

For **rest endpoints and exceptions** please refer to the [API Document](https://documenter.getpostman.com/view/17097842/UV5c8uab/)

<br />

## Payment Service

One **DTO**, TransactionDetailsDto is used to handle requests and give response to
clients.

**Model Mapper** is used to convert entity to DTO and vice versa. This conversion is
handled in PaymentServiceImpl class.

For **rest endpoints and exceptions** please refer to the [API Document](https://documenter.getpostman.com/view/17097842/UV5c8uab/)

<br />

#

> Please note: When communicating through port:9191, hitting endpoints
immediately upon starting application seems to be giving 503 Service unavailable
response. Service shows “RoundRobinLoadBalancer : No servers available for
service” error. Kindly wait for a few minutes before hitting endpoints. It usually
works for me but if it doesn’t endpoints can always be hit on their respective port
numbers.
