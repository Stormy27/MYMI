# Brief description of the SmartMeterReaderTask.
Application has exposed one endpoint for registration smart meters.<p>
There is scheduler which read data from smart meter every 10 minutes at the start of the hour .
## Suggestions for improvement
### Logger
- Add logback
- Configure suitable appender
- Add SLF4J

### WebClient
Currently, application can read only from one endpoint due to creating
`WebClient` as Bean. If we want to read from more endpoints, we have to create
`WebClient` with adjustable uri or create WebClient for each endpoint.

### Validation
Currently, only basic validation is implemented for incoming data. 
For production environments, we should implement additional validation
as specified in the documentation or in the application's requirements.

### Incoming data
Application accept only consist data, inconsistent data aren´t 
stored into database. There is no policy what to do with inconsistent data (JSON) in task.
For improvement, we can store inconsistent data and merge with data which come later.

Application can't store measurement results for smart meter which wasn't registered before.
These kind of data we can store and insert later (after smart meter registration).

If smart meter ins't store in DB, we get it from measurement results and save it before saving measurement.

### Security
Application doesn't have set up security policy. 
To se up security we can add Spring Security Dependency and set up `HttpSecurity`.

### Database
The `timestamp` property of the `Measurement` entity is of type `ZonedDateTime`.
Some older databases or versions may not fully support this data type. 
In such cases, it may be necessary to use `Instant` or `LocalDateTime` and handle time zone conversions in the application layer.

Credentials is stored in application properties. Better to provide credentials via environment variables or stored it 
in encrypted files. However, could be use any management tool.