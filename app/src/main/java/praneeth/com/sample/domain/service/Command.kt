package praneeth.com.sample.domain.service

/**
 * Created by Praneeth on 2019-10-17.
 */

public interface Command<out T> {
    fun execute(): T
}