package edu.ntnu.stud.traindeparture;

import java.time.LocalTime;

/**
 * Class for the train departures.
 * <p>Will store information such as train number, line, departure time, delay,
 * destination and what track it will depart from.</p>
 *
 * @author Sigurd Riseth
 * @version 0.0.1
 * @since 14.10.2023
 */
public class TrainDeparture {

  private int track;
  private final int trainNumber;
  private final String line;
  private final String destination;
  private LocalTime departureTime; // use a Clock class to store departureTime and delay.
  private LocalTime delay;

  /**
   * Constructor that sets all parameters of the class to the given parameter except delay which is
   * default 00:00.
   *
   * @param track         track the train will depart from
   * @param trainNumber   unique train ID
   * @param line          the line the train is driving
   * @param destination   the ends destination of the train
   * @param departureTime the time the train is set to depart
   */
  public TrainDeparture(String track, int trainNumber, String line, String destination,
      LocalTime departureTime) {
    this.setTrack(track);
    this.trainNumber = trainNumber;
    this.line = line;
    this.destination = destination;
    this.setDepartureTime(departureTime);
    this.delay = LocalTime.of(0, 0);
  }

  /**
   * Retrieves the track number as an integer.
   *
   * @return An integer representing the track number.
   */

  public int getTrack() {
    return track;
  }

  /**
   * Sets the track. If the track is under 1 or text the value is set to -1.
   */
  public void setTrack(String track) {
    try {
      if (Integer.parseInt(track) > 0) {
        this.track = Integer.parseInt(track);
      } else {
        this.track = -1;
      }
    } catch (Exception e) {
      this.track = -1;
    }
  }

  /**
   * Retrieves the train number as an integer.
   *
   * @return An integer representing the train number.
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Retrieves the line information as a String.
   *
   * @return A String containing information about the line.
   */
  public String getLine() {
    return line;
  }

  /**
   * Retrieves the destination of this entity as a String.
   *
   * @return A String representing the destination.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Retrieves the departure time (delay included) as a LocalTime object.
   *
   * @return The LocalTime object representing the departure time.
   */
  public LocalTime getDepartureTime() {
    return departureTime;
  }

  /**
   * Sets the departure time. Can only be set to a time after the current time.
   *
   * @param departureTime departure time to be set
   */
  public void setDepartureTime(LocalTime departureTime) {
    this.departureTime = departureTime;
  }

  /**
   * Retrieves the delay represented as a LocalTime.
   *
   * @return The LocalTime object representing the delay.
   */
  public LocalTime getDelay() {
    return delay;
  }

  /**
   * Sets the delay. If the delay is null the delay is set to 00:00. Removed from station if delayed
   * over midnight
   *
   * @param delay delay to be set
   */
  public void setDelay(LocalTime delay) {
    // TODO: delay burde kanskje ikke være 00:00 om man får inn null?
    // TODO: Kan man endre delay slik at reel deparutre time blir før klokken?
    if (delay != null) {
      this.delay = delay;
    } else {
      this.delay = LocalTime.of(0, 0);
    }
  }
}
