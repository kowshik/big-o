package general;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sorting.Heap;
import sorting.SimpleHeapImpl;
import exceptions.EmptyHeapException;

public class MinimumMeetingRooms {
	public static int findMinimumMeetingRooms(List<Schedule> schedules,
			final Comparator<Time> timeComparator) {
		Comparator<Schedule> scheduleComparator = new Comparator<Schedule>() {
			@Override
			public int compare(Schedule foo, Schedule bar) {
				return timeComparator.compare(foo.getStartTime(),
						bar.getStartTime());
			}
		};

		Collections.sort(schedules, scheduleComparator);

		MeetingRoom meetingRoom = new MeetingRoom();
		Comparator<MeetingRoom> meetingRoomComparator = new Comparator<MeetingRoom>() {
			@Override
			public int compare(MeetingRoom foo, MeetingRoom bar) {
				return timeComparator.compare(foo.getEarliestStartTime(),
						bar.getEarliestStartTime());
			}
		};

		Heap<MeetingRoom> heap = new SimpleHeapImpl<MeetingRoom>(
				meetingRoomComparator);
		heap.add(meetingRoom);

		for (Schedule s : schedules) {
			try {
				meetingRoom = heap.pop();
			} catch (EmptyHeapException e) {
				// This should never happen.
				throw new RuntimeException(e);
			}

			if (timeComparator.compare(meetingRoom.getEarliestStartTime(),
					s.getStartTime()) < 0) {
				meetingRoom.setEarliestStartTime(s.getEndTime());
			} else {
				meetingRoom = new MeetingRoom(s.getEndTime());
			}

			heap.add(meetingRoom);
		}

		return heap.size();
	}
}
