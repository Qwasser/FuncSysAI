package ru.nsu.alife.fs;

/**
 * Interface introducing any action in system.
 */
public interface IAction {
    /**
     * Method will be called by rule during rule execution.
     *
     * @return true if executed
     *         false if not
     */
    public boolean doAction();
}
