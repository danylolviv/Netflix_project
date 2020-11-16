package DAL.exception;

import java.io.IOException;

public class MrsDalException extends Exception{

    public MrsDalException()
    {
        super();
    }

    public MrsDalException(String message)
    {
        super(message);
    }

    public MrsDalException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MrsDalException(String message, Exception ex)
    {
        super(message, ex);
    }
}
