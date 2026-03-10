package com.merlottv.app.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.merlottv.app.domain.repository.ChannelRepository
import com.merlottv.app.domain.repository.EpgRepository
import com.merlottv.app.domain.repository.SettingsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit

/**
 * Periodic WorkManager worker that refreshes M3U playlists in the background.
 * Interval is driven by the user's setting (default: 24 h).
 */
@HiltWorker
class PlaylistRefreshWorker @AssistedInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters,
    private val channelRepo: ChannelRepository,
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        return try {
            channelRepo.refreshAllSources()
            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 3) Result.retry() else Result.failure()
        }
    }

    companion object {
        private const val WORK_NAME = "playlist_refresh"

        fun schedule(context: Context, intervalHours: Int) {
            if (intervalHours == 0) {
                WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
                return
            }
            val request = PeriodicWorkRequestBuilder<PlaylistRefreshWorker>(
                intervalHours.toLong(), TimeUnit.HOURS,
                15L, TimeUnit.MINUTES,   // flex period
            )
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                request,
            )
        }
    }
}

/**
 * Periodic WorkManager worker that refreshes EPG data.
 * Also prunes programme entries older than 24 h.
 */
@HiltWorker
class EpgRefreshWorker @AssistedInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters,
    private val epgRepo: EpgRepository,
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        return try {
            epgRepo.refreshAllEpg()
            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 3) Result.retry() else Result.failure()
        }
    }

    companion object {
        private const val WORK_NAME = "epg_refresh"

        fun schedule(context: Context, intervalHours: Int) {
            if (intervalHours == 0) {
                WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
                return
            }
            val request = PeriodicWorkRequestBuilder<EpgRefreshWorker>(
                intervalHours.toLong(), TimeUnit.HOURS,
                30L, TimeUnit.MINUTES,
            )
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                request,
            )
        }
    }
}
